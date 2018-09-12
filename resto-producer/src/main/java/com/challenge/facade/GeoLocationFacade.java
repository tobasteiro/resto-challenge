package com.challenge.facade;

import com.challenge.exception.GeolocationException;
import com.challenge.model.Location;

public interface GeoLocationFacade {

  /**
   * @param origin location.
   * @param destination location.
   * @return ETA between origin and destination.s
   * @throws GeolocationException 
   */
  String calculateETA(Location origin, Location destination) throws GeolocationException;
  
}
