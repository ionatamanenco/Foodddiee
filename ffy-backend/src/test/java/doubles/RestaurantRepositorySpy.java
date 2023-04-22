package doubles;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class RestaurantRepositorySpy implements RestaurantRepository {

    private final RestaurantRepository realRepository;
    private final List<Restaurant> savedRestaurants = new ArrayList<>();

    public RestaurantRepositorySpy(RestaurantRepository realRepository) {
        this.realRepository = realRepository;
    }

    @Override
    public List<Restaurant> findAll(Sort sort) {
        // delegate to the real repository
        return realRepository.findAll(sort);
    }

    @Override
    public <S extends Restaurant> S save(S s) {
        // save the restaurant to the spy
        savedRestaurants.add(s);
        // delegate to the real repository
        return realRepository.save(s);
    }

    public List<Restaurant> getSavedRestaurants() {
        return savedRestaurants;
    }
}
