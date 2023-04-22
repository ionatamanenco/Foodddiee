package restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import ia.ffy.foodforyou.restaurant.RestaurantDTO;
import ia.ffy.foodforyou.restaurant.RestaurantService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AllArgsConstructor
class RestaurantControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        // Delete all restaurants before each test
        restaurantService.deleteAll();
    }

    @Test
    void shouldGetAllRestaurants() throws Exception {
        // Given
        RestaurantDTO restaurantDTO = createRestaurantDTO();
        restaurantService.create(restaurantDTO);

        // When
        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(restaurantDTO))));
    }

    @Test
    void shouldGetRestaurantById() throws Exception {
        // Given
        RestaurantDTO restaurantDTO = createRestaurantDTO();
        restaurantService.create(restaurantDTO);

        // When
        mockMvc.perform(get("/api/restaurants/{id}", restaurantDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(restaurantDTO)));
    }

    @Test
    void shouldCreateRestaurant() throws Exception {
        // Given
        RestaurantDTO restaurantDTO = createRestaurantDTO();

        // When
        mockMvc.perform(post("/api/restaurants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().isCreated());

        // Then
        assertEquals(1, restaurantService.findAll().size());
    }

    @Test
    void shouldUpdateRestaurant() throws Exception {
        // Given
        RestaurantDTO restaurantDTO = createRestaurantDTO();
        restaurantService.create(restaurantDTO);

        restaurantDTO.setCompanyName("New Company Name");

        // When
        mockMvc.perform(put("/api/restaurants/{id}", restaurantDTO.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(restaurantDTO)))
                .andExpect(status().isOk());

        // Then
        assertEquals("New Company Name", restaurantService.get(restaurantDTO.getId()).getCompanyName());
    }

    @Test
    void shouldDeleteRestaurant() throws Exception {
        // Given
        RestaurantDTO restaurantDTO = createRestaurantDTO();
        restaurantService.create(restaurantDTO);

        // When
        mockMvc.perform(delete("/api/restaurants/{id}", restaurantDTO.getId()))
                .andExpect(status().isNoContent());

        // Then
        assertEquals(0, restaurantService.findAll().size());
    }

    private RestaurantDTO createRestaurantDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(UUID.randomUUID().toString());
        restaurantDTO.setCompanyName("Test Company");
        restaurantDTO.setCompanyLogo("https://test-company.com/logo.png");
        restaurantDTO.setCompanyDescription("Test Company Description");
        return restaurantDTO;
    }
}
