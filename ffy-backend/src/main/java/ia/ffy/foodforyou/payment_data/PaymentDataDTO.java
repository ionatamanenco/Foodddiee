package ia.ffy.foodforyou.payment_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentDataDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String card;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Size(max = 255)
    @JsonProperty("cVV")
    private String cVV;

    @Size(max = 255)
    private String userPaymentInfo;

}
