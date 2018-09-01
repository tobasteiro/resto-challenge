package com.challenge.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class RestaurantBasicInformationDto {

  private String logoUrl;
  @NotNull
  private String commercialName;
  @NotNull
  private String legalName;
  private BigDecimal rating;
  @NotNull
  private String commercialEmail;
  private String adminNumber;
  @NotNull
  private String address;
  @NotNull
  private LocationDto location;

  public RestaurantBasicInformationDto(String logoUrl, String commercialName, String legalName,
      BigDecimal rating, String commercialEmail, String adminNumber, String address,
      LocationDto location) {
    super();
    this.logoUrl = logoUrl;
    this.commercialName = commercialName;
    this.legalName = legalName;
    this.rating = rating;
    this.commercialEmail = commercialEmail;
    this.adminNumber = adminNumber;
    this.address = address;
    this.location = location;
  }

  public String getLogoUrl() {
    return logoUrl;
  }

  public String getCommercialName() {
    return commercialName;
  }

  public String getLegalName() {
    return legalName;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public String getCommercialEmail() {
    return commercialEmail;
  }

  public String getAdminNumber() {
    return adminNumber;
  }

  public String getAddress() {
    return address;
  }

  public LocationDto getLocation() {
    return location;
  }

}
