package ia.ffy.foodforyou.restaurant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String companyName;

    @Size(max = 255)
    private String companyLogo;

    @Size(max = 255)
    private String companyDescription;
}
