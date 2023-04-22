package ia.ffy.foodforyou.menu;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuDTO {

    private String id;

    @Size(max = 255)
    private String position;

    private Double price;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String photo;

    @NotNull
    @Size(max = 255)
    private String restaurantMenu;

    private List<String> menuCategories;

}
