package com.challenge.model;

import com.google.maps.model.LatLng;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Location {
  double latitude;
  double longitude;
  @Transient
  LatLng location;

  public Location() {
    super();
  }

  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setLocation(LatLng location) {
    this.location = location;
  }

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }

}
