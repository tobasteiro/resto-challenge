package com.challenge.util;

import com.challenge.dto.LocationDto;
import com.challenge.model.Location;

import java.util.Objects;

public class LocationUtils {

  /**
   * @param location from db.
   * @return location dto.
   */
  public static LocationDto createLocation(Location location) {
    if (Objects.isNull(location)) {
      return null;
    }
    return new LocationDto(location.getLatitude(), location.getLongitude());
  }

  /**
   * @param location from dto.
   * @return location db.
   */
  public static Location createLocation(LocationDto location) {
    if (Objects.isNull(location)) {
      return null;
    }
    return new Location(location.getLatitude(), location.getLongitude());
  }

}
