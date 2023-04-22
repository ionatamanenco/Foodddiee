package ia.ffy.foodforyou.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    boolean existsByIdIgnoreCase(String id);

}
