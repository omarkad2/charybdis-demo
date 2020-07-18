package ma.markware.charybdis.demo.spring.dto;

public class AddressDto {

  private int number;
  private String street;
  private String city;
  private CountryDto country;

  public int getNumber() {
    return number;
  }

  public void setNumber(final int number) {
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

  public CountryDto getCountry() {
    return country;
  }

  public void setCountry(final CountryDto country) {
    this.country = country;
  }
}
