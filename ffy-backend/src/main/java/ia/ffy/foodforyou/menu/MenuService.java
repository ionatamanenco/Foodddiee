package ia.ffy.foodforyou.menu;

import ia.ffy.foodforyou.category.Category;
import ia.ffy.foodforyou.category.CategoryRepository;
import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    public MenuService(final MenuRepository menuRepository,
                       final RestaurantRepository restaurantRepository,
                       final CategoryRepository categoryRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MenuDTO> findAll() {
        final List<Menu> menus = menuRepository.findAll(Sort.by("id"));
        return menus.stream()
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .collect(Collectors.toList());
    }

    public MenuDTO get(final String id) {
        return menuRepository.findById(id)
                .map(menu -> mapToDTO(menu, new MenuDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final MenuDTO menuDTO) {
        final Menu menu = new Menu();
        mapToEntity(menuDTO, menu);
        return menuRepository.save(menu).getId();
    }

    public void update(final String id, final MenuDTO menuDTO) {
        final Menu menu = menuRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(menuDTO, menu);
        menuRepository.save(menu);
    }

    public void delete(final String id) {
        menuRepository.deleteById(id);
    }

    private MenuDTO mapToDTO(final Menu menu, final MenuDTO menuDTO) {
        menuDTO.setId(menu.getId());
        menuDTO.setPosition(menu.getPosition());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setPhoto(menu.getPhoto());
        menuDTO.setRestaurantMenu(menu.getRestaurantMenu() == null ? null : menu.getRestaurantMenu().getId());
        menuDTO.setMenuCategories(menu.getMenuCategoryCategories() == null ? null : menu.getMenuCategoryCategories().stream()
                .map(Category::getId)
                .toList());
        return menuDTO;
    }

    private Menu mapToEntity(final MenuDTO menuDTO, final Menu menu) {
        menu.setPosition(menuDTO.getPosition());
        menu.setPrice(menuDTO.getPrice());
        menu.setDescription(menuDTO.getDescription());
        menu.setPhoto(menuDTO.getPhoto());
        final Restaurant restaurantMenu = menuDTO.getRestaurantMenu() == null ? null : restaurantRepository.findById(menuDTO.getRestaurantMenu())
                .orElseThrow(() -> new NotFoundException("Restaurant Menu not found!"));
        menu.setRestaurantMenu(restaurantMenu);
        final List<Category> menuCategories = categoryRepository.findAllById(
                menuDTO.getMenuCategories() == null ? Collections.emptyList() : menuDTO.getMenuCategories());
        if (menuCategories.size() != (menuDTO.getMenuCategories() == null ? 0 : menuDTO.getMenuCategories().size())) {
            throw new NotFoundException("one of Menu categories not found!");
        }
        menu.setMenuCategoryCategories(new HashSet<>(menuCategories));
        return menu;
    }

}
