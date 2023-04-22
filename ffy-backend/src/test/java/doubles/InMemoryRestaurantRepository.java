package doubles;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryRestaurantRepository implements RestaurantRepository {

    private final Map<String, Restaurant> restaurants = new HashMap<>();

    @Override
    public List<Restaurant> findAll(Sort sort) {
        return new ArrayList<>(restaurants.values());
    }

    @Override
    public Optional<Restaurant> findById(String s) {
        return Optional.ofNullable(restaurants.get(s));
    }

    @Override
    public <S extends Restaurant> S save(S s) {
        restaurants.put(s.getId(), s);
        return s;
    }

    @Override
    public void deleteById(String s) {
        restaurants.remove(s);
    }

    @Override
    public void deleteAll() {
        restaurants.clear();
    }
}
