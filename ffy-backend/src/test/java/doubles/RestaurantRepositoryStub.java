package doubles;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

public abstract class RestaurantRepositoryStub implements RestaurantRepository {

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
        // just return the input object without actually saving it to a database
        return s;
    }

}
