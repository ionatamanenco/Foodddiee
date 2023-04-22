package ia.ffy.foodforyou.login.security.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String firstname;

    @Size(max = 255)
    private String lastname;

    @NotNull
    @Size(max = 255)
    private String password;

    private Role role;

    @Size(max = 255)
    private String userRestaurant;

}