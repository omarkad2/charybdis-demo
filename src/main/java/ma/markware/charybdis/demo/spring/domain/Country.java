package ma.markware.charybdis.demo.spring.domain;

import ma.markware.charybdis.model.annotation.Udt;
import ma.markware.charybdis.model.annotation.UdtField;

@Udt(keyspace = "keyspace_demo", name = "country")
public class Country {

  @UdtField(name = "country_name")
  private String countryName;

  @UdtField(name= "country_code")
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
