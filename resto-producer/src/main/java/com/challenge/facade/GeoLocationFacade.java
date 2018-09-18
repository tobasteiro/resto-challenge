package com.challenge.facade;

import com.challenge.dto.LocationDto;
import com.challenge.exception.GeolocationException;

public interface GeoLocationFacade {

  /**
   * @param origin location.
   * @param destination location.
   * @return ETA between origin and destination.s
   * @throws GeolocationException
   */
  String calculateETA(LocationDto origin, LocationDto destination) throws GeolocationException;

}
