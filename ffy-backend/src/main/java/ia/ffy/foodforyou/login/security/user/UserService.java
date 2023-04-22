package ia.ffy.foodforyou.login.security.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ia.ffy.foodforyou.login.security.user.User;
import ia.ffy.foodforyou.login.security.user.UserDTO;
import ia.ffy.foodforyou.login.security.user.UserRepository;
import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public UserService(final UserRepository userRepository,
                       final RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserDTO get(final String id) {
        return userRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(NotFoundException::new);
    }

    public String create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        user.setId(userDTO.getId());
        return userRepository.save(user).getId();
    }

    public void update(final String id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final String id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setUserRestaurant(user.getUserRestaurant() == null ? null : user.getUserRestaurant().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        final Restaurant userRestaurant = userDTO.getUserRestaurant() == null ? null : restaurantRepository.findById(userDTO.getUserRestaurant())
                .orElseThrow(() -> new NotFoundException("userRestaurant not found"));
        user.setUserRestaurant(userRestaurant);
        return user;
    }

    public boolean idExists(final String id) {
        return userRepository.existsByIdIgnoreCase(id);
    }

    public UserDTO findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return mapToDTO(user.get());
    }
}