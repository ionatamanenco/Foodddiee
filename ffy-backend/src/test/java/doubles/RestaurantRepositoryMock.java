package doubles;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RestaurantRepositoryMock implements RestaurantRepository {

    private final List<Restaurant> savedRestaurants = new ArrayList<>();

    @Override
    public List<Restaurant> findAll(Sort sort) {
        // return a list of test data
        return Arrays.asList(
                new Restaurant("1", "Restaurant 1", "logo1.png", "Description 1"),
                new Restaurant("2", "Restaurant 2", "logo2.png", "Description 2")
        );
    }

    @Override
    public <S extends Restaurant> S save(S s) {
        // save the restaurant to the mock
        savedRestaurants.add(s);
        // just return the input object without actually saving it to a database
        return s;
    }

    public List<Restaurant> getSavedRestaurants() {
        return savedRestaurants;
    }
}
