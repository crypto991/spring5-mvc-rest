package crypto.org.api.v1.mapper;


import crypto.org.api.v1.model.CustomerDTO;
import crypto.org.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO (Customer customer);
}
