package ia.ffy.foodforyou.category;

import ia.ffy.foodforyou.menu.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "menuCategoryCategories")
    private Set<Menu> menuCategoryMenus;

}
