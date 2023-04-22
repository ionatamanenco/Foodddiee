package menu;

import ia.ffy.foodforyou.category.Category;
import ia.ffy.foodforyou.category.CategoryRepository;
import ia.ffy.foodforyou.menu.Menu;
import ia.ffy.foodforyou.menu.MenuDTO;
import ia.ffy.foodforyou.menu.MenuRepository;
import ia.ffy.foodforyou.menu.MenuService;
import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
class MenuServiceTest {

    private MenuRepository menuRepository;

    private RestaurantRepository restaurantRepository;

    private CategoryRepository categoryRepository;

    @InjectMocks
    private MenuService menuService;

    private MenuDTO menuDTO;
    private Menu menu;

    @BeforeEach
    void setUp() {
        // Create a sample menu DTO
        menuDTO = new MenuDTO();
        menuDTO.setPosition("1");
        menuDTO.setPrice(10.99);
        menuDTO.setDescription("Sample menu item");
        menuDTO.setPhoto("https://sample-photo-url.com");
        menuDTO.setRestaurantMenu("1");
        menuDTO.setMenuCategories(List.of("1", "2"));

        // Create a sample menu entity
        menu = new Menu();
        menu.setId("1");
        menuDTO.setPosition("1");
        menu.setPrice(10.99);
        menu.setDescription("Sample menu item");
        menu.setPhoto("https://sample-photo-url.com");
        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        menu.setRestaurantMenu(restaurant);
        Category category1 = new Category();
        category1.setId("1");
        Category category2 = new Category();
        category2.setId("2");
        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        menu.setMenuCategoryCategories(categories);
    }

    @Test
    void testFindAll_ReturnsListOfMenus() {
        // Mock the behavior of the menu repository
        when(menuRepository.findAll(Sort.by("id"))).thenReturn(List.of(menu));

        // Call the service method
        List<MenuDTO> result = menuService.findAll();

        // Verify the results
        assertEquals(1, result.size());
        MenuDTO resultDTO = result.get(0);
        assertEquals(menu.getId(), resultDTO.getId());
        assertEquals(menu.getPosition(), resultDTO.getPosition());
        assertEquals(menu.getPrice(), resultDTO.getPrice());
        assertEquals(menu.getDescription(), resultDTO.getDescription());
        assertEquals(menu.getPhoto(), resultDTO.getPhoto());
        assertEquals(menu.getRestaurantMenu().getId(), resultDTO.getRestaurantMenu());
        assertEquals(menu.getMenuCategoryCategories().stream().map(Category::getId).toList(), resultDTO.getMenuCategories());
    }

    @Test
    void testGet_ReturnsMenuWithGivenId() {
        // Mock the behavior of the menu repository
        when(menuRepository.findById(menu.getId())).thenReturn(Optional.of(menu));

        // Call the service method
        MenuDTO result = menuService.get(menu.getId());

        // Verify the results
        assertEquals(menu.getId(), result.getId());
        assertEquals(menu.getPosition(), result.getPosition());
        assertEquals(menu.getPrice(), result.getPrice());
        assertEquals(menu.getDescription(), result.getDescription());
        assertEquals(menu.getPhoto(), result.getPhoto());
        assertEquals(menu.getRestaurantMenu().getId(), result.getRestaurantMenu());
        assertEquals(menu.getMenuCategoryCategories().stream().map(Category::getId).toList(), result.getMenuCategories());
    }

    @Test
    void testGet_ThrowsNotFoundExceptionIfMenuNotFound() {
        // Mock the behavior of the menu repository
        when(menuRepository.findById(menu.getId())).thenReturn(Optional.empty());

        // Verify that a NotFoundException is thrown when the method is called
        assertThrows(NotFoundException.class, () -> menuService.get(menu.getId()));
    }

    @Test
    void testCreate_ReturnsIdOfCreatedMenu() {
        final MenuDTO menuDTO = new MenuDTO();
        menuDTO.setPosition(menu.getPosition());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPhoto(menu.getPhoto());

        // Mock the behavior of the menu repository
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        // Invoke the method being tested
        final String id = menuService.create(menuDTO);

        // Verify the results
        assertNotNull(id);
        assertEquals(menu.getId(), id);
    }
}