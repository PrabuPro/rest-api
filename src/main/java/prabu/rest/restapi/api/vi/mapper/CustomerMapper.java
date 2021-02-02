package prabu.rest.restapi.api.vi.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import prabu.rest.restapi.api.vi.model.CustomerDTO;
import prabu.rest.restapi.domain.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
