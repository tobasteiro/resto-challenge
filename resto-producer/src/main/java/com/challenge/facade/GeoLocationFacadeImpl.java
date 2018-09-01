package com.challenge.facade;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeoLocationFacadeImpl implements GeoLocationFacade {

  @Value("${google.maps.api.key}")
  private String apiKey;

  @Override
  public String calculateETA(LatLng origin, LatLng destination) {
    GeoApiContext context = new GeoApiContext().setApiKey(apiKey);

    DistanceMatrix trix = null;
    try {
      DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
      // TravelMode set to driving for motorcycle.
      trix = req.origins(origin).destinations(destination).mode(TravelMode.DRIVING)
          .language("es-ES").await();

    } catch (ApiException e) {
      System.out.println(e.getMessage());
      // TODO throw custom exception
    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO throw custom exception
    }

    return trix.rows[0].elements[0].duration.humanReadable;
  }

}
