package crypto.org.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    private Long id;

    private String name;

    private String vendorUrl;

    public VendorDTO(String name, String vendorUrl) {
        this.name = name;
        this.vendorUrl = vendorUrl;
    }
}
