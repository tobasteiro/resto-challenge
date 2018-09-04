package com.challenge.dto;

public class LocationDto {
  double latitude;
  double longitude;

  public LocationDto(double latitude, double longitude) {
    super();
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public LocationDto() {
    super();
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

}
