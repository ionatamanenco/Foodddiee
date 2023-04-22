package ia.ffy.foodforyou.sitting_tables;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SittingTablesDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    private Integer number;

    @NotNull
    @Size(max = 255)
    private String qrCode;

    @Size(max = 255)
    private String restaurantTables;

}
