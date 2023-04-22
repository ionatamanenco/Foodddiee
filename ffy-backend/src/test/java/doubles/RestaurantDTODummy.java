package doubles;

import ia.ffy.foodforyou.restaurant.RestaurantDTO;

public class RestaurantDTODummy extends RestaurantDTO {

    // all fields are null by default
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
        // do nothing
    }

}