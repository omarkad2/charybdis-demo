package ma.markware.charybdis.demo.spring.domain;

import ma.markware.charybdis.model.annotation.Frozen;
import ma.markware.charybdis.model.annotation.Udt;
import ma.markware.charybdis.model.annotation.UdtField;

@Udt(keyspace = "keyspace_demo", name = "address")
public class Address {

  @UdtField
  private Integer number; // Avoid using primitive types in model class!

  @UdtField
  private String street;

  @UdtField
  private String city;

  @UdtField
  private @Frozen Country country;

  public Integer getNumber() {
    return number;
  }

  public void setNumber(final Integer number) {
    this.number = number;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(final String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }
}
