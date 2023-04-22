package ia.ffy.foodforyou.category;

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
@RequestMapping(value = "/api/categories")
public class CategoryController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
  private final CategoryService categoryService;

  public CategoryController(final CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    LOGGER.info("Received a request to fetch all categories...");
    return ResponseEntity.ok(categoryService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategory(@PathVariable final String id) {
    return ResponseEntity.ok(categoryService.get(id));
  }

  @PostMapping
  public ResponseEntity<Void> createCategory(@RequestBody @Valid final CategoryDTO categoryDTO) {
    categoryService.create(categoryDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCategory(@PathVariable final String id,
                                             @RequestBody @Valid final CategoryDTO categoryDTO) {
    categoryService.update(id, categoryDTO);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable final String id) {
    categoryService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
