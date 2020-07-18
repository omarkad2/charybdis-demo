package ma.markware.charybdis.demo.spring.mapper;

import ma.markware.charybdis.demo.spring.domain.Address;
import ma.markware.charybdis.demo.spring.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address toSource(AddressDto addressDto);

  AddressDto toDto(Address address);
}
