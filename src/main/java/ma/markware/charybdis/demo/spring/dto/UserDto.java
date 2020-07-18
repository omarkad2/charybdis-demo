package ma.markware.charybdis.demo.spring.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import ma.markware.charybdis.demo.spring.domain.Address;
import ma.markware.charybdis.demo.spring.domain.RoleEnum;

public class UserDto {

  private String name;
  private List<Address> addresses;
  private RoleEnum role;
  private Map<Instant, String> accessLogs;
  private AddressDto address;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(final List<Address> addresses) {
    this.addresses = addresses;
  }

  public RoleEnum getRole() {
    return role;
  }

  public void setRole(final RoleEnum role) {
    this.role = role;
  }

  public Map<Instant, String> getAccessLogs() {
    return accessLogs;
  }

  public void setAccessLogs(final Map<Instant, String> accessLogs) {
    this.accessLogs = accessLogs;
  }

  public AddressDto getAddress() {
    return address;
  }

  public void setAddress(final AddressDto address) {
    this.address = address;
  }
}
