package prabu.rest.restapi.api.vi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import prabu.rest.restapi.api.vi.model.VendorDTO;
import prabu.rest.restapi.domain.Vendor;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor ventorDTOToVendor(VendorDTO vendorDTO);

}
