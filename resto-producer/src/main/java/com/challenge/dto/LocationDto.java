package com.challenge.dto;

public class LocationDto {
  //TODO validate format ##.######
  double latitude;
  double longitude;

  public LocationDto(double latitude, double longitude) {
    super();
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

}
