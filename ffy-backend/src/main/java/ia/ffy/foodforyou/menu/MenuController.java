package ia.ffy.foodforyou.menu;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/menus")
public class MenuController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
  private final MenuService menuService;

  public MenuController(final MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping
  public ResponseEntity<List<MenuDTO>> getAllMenus() {
    return ResponseEntity.ok(menuService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MenuDTO> getMenu(@PathVariable final String id) {
    LOGGER.info("Received a request to fetch menu with ID [{}].", id);
    return ResponseEntity.ok(menuService.get(id));
  }

  @PostMapping
  public ResponseEntity<String> createMenu(@RequestBody @Valid final MenuDTO menuDTO) {
    LOGGER.info("Received a request to create menu with the following data [{}].", menuDTO);
    return new ResponseEntity<>(menuService.create(menuDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateMenu(@PathVariable final String id,
                                         @RequestBody @Valid final MenuDTO menuDTO) {
    LOGGER.info("Received a request to update menu with ID [{}] with the following data [{}].", id, menuDTO);
    menuService.update(id, menuDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMenu(@PathVariable final String id) {
    LOGGER.info("Received a request to delete menu with ID [{}].", id);
    menuService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
