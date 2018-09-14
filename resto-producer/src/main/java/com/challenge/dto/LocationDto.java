package com.challenge.dto;

import com.challenge.model.Location;

public class LocationDto {
  //TODO validate format ##.######
  Double latitude;
  Double longitude;

  public LocationDto(Double latitude, Double longitude) {
    super();
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  public LocationDto() {
    super();
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LocationDto) {
      LocationDto other = (LocationDto) obj;
      return other.latitude == this.latitude && other.longitude == this.longitude;
    }
    if (obj instanceof Location) {
      Location other = (Location) obj;
      return other.getLatitude() == this.latitude && other.getLongitude() == this.longitude;
    }
    return false;
  }

}
