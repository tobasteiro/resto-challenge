package com.challenge.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "logo")
  private String logo;

  @Column(name = "commercial_name", nullable = false)
  private String commercialName;

  @Column(name = "legal_name", nullable = false)
  private String legalName;

  @Column(name = "rating")
  private BigDecimal rating;

  @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Review> reviews;

  @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Meal> meals;

  @Column(name = "commercial_email", nullable = false)
  private String commercialEmail;

  @Column(name = "admin_number")
  private String adminNumber;

  @Column(name = "address", nullable = false)
  private String address;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "latitude")), 
      @AttributeOverride(name = "longitude", column = @Column(name = "longitude"))})
  private Location location;

  public Restaurant() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getCommercialName() {
    return commercialName;
  }

  public void setCommercialName(String commercialName) {
    this.commercialName = commercialName;
  }

  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
  }

  public String getCommercialEmail() {
    return commercialEmail;
  }

  public void setCommercialEmail(String commercialEmail) {
    this.commercialEmail = commercialEmail;
  }

  public String getAdminNumber() {
    return adminNumber;
  }

  public void setAdminNumber(String adminNumber) {
    this.adminNumber = adminNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
