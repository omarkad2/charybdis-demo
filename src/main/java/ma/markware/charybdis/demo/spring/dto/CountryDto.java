package ma.markware.charybdis.demo.spring.dto;

public class CountryDto {

  private String countryName;
  private String countryCode;

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(final String countryName) {
    this.countryName = countryName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }
}
