package crypto.org.api.v1.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {


    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;

    @ApiModelProperty(required = true) //removes Optional
    private String lastName;

    private String customerUrl;
}
