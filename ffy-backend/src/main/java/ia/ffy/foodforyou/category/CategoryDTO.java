package ia.ffy.foodforyou.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

}
