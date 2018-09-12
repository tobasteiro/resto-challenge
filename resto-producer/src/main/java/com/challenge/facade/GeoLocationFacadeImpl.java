package com.challenge.facade;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.challenge.exception.GeolocationException;
import com.challenge.model.Location;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Duration;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

@Component
public class GeoLocationFacadeImpl implements GeoLocationFacade {

	@Value("${google.maps.api.key}")
	private String apiKey;

	@Override
	public String calculateETA(Location origin, Location destination) throws GeolocationException {
		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);

		DistanceMatrix trix = null;
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			LatLng originApi = new LatLng(origin.getLatitude(), origin.getLongitude());
			LatLng destinationApi = new LatLng(destination.getLatitude(), destination.getLongitude());
			// TravelMode set to driving for motorcycle.
			trix = req.origins(originApi).destinations(destinationApi).mode(TravelMode.DRIVING).language("es-ES")
					.await();
		} catch (Exception e) {
			// PendingResultBase.await throws generic Exception
			throw new GeolocationException("Error calling google maps api", e);
		}

		Duration duration = Objects.nonNull(trix) ? trix.rows[0].elements[0].duration : null;
		return Objects.nonNull(duration) ? duration.humanReadable : "N/A";
	}

}
