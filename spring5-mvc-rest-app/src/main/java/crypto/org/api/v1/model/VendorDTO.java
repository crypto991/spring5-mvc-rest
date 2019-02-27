package crypto.org.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {


    @ApiModelProperty(value = "this is Vendor name", required = true)
    private String name;

    private String vendorUrl;


}
