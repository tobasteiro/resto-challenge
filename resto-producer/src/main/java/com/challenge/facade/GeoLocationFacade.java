package com.challenge.facade;

import com.google.maps.model.LatLng;

public interface GeoLocationFacade {

  /**
   * @param origin location.
   * @param destination location.
   * @return ETA between origin and destination.s
   */
  String calculateETA(LatLng origin, LatLng destination);

}
