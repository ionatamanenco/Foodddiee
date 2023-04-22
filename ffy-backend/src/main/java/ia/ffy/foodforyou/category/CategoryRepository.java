package ia.ffy.foodforyou.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByIdIgnoreCase(String id);

}
