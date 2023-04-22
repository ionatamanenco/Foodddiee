package ia.ffy.foodforyou.restaurant;

import ia.ffy.foodforyou.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> findAll() {
        final List<Restaurant> restaurants = restaurantRepository.findAll(Sort.by("id"));
        return restaurants.stream()
                .map(restaurant -> mapToDTO(restaurant, new RestaurantDTO()))
                .collect(Collectors.toList());
    }

    public RestaurantDTO get(final String id) {
        return restaurantRepository.findById(id)
                .map(restaurant -> mapToDTO(restaurant, new RestaurantDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Restaurant create(final RestaurantDTO restaurantDTO) {
        final Restaurant restaurant = new Restaurant();
        mapToEntity(restaurantDTO, restaurant);
        restaurant.setId(restaurantDTO.getId());
        return restaurantRepository.save(restaurant);
    }

    public void update(final String id, final RestaurantDTO restaurantDTO) {
        final Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(restaurantDTO, restaurant);
        restaurantRepository.save(restaurant);
    }

    public void delete(final String id) {
        restaurantRepository.deleteById(id);
    }

    public void deleteAll() {
        restaurantRepository.deleteAll();
    }

    private RestaurantDTO mapToDTO(final Restaurant restaurant, final RestaurantDTO restaurantDTO) {
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setCompanyName(restaurant.getCompanyName());
        restaurantDTO.setCompanyLogo(restaurant.getCompanyLogo());
        restaurantDTO.setCompanyDescription(restaurant.getCompanyDescription());
        return restaurantDTO;
    }

    private Restaurant mapToEntity(final RestaurantDTO restaurantDTO, final Restaurant restaurant) {
        restaurant.setCompanyName(restaurantDTO.getCompanyName());
        restaurant.setCompanyLogo(restaurantDTO.getCompanyLogo());
        restaurant.setCompanyDescription(restaurantDTO.getCompanyDescription());
        return restaurant;
    }

    public boolean idExists(final String id) {
        return restaurantRepository.existsByIdIgnoreCase(id);
    }

}
