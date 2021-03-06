package prabu.rest.restapi.api.vi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
