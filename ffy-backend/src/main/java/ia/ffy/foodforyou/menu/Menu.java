package ia.ffy.foodforyou.menu;

import ia.ffy.foodforyou.category.Category;
import ia.ffy.foodforyou.order.Order;
import ia.ffy.foodforyou.restaurant.Restaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Menu {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private String id;

    @Column
    private String position;

    @Column
    private Double price;

    @Column
    private String description;

    @Column
    private String photo;

    @OneToOne
    @JoinColumn(name = "restaurant_menu_id", nullable = false)
    private Restaurant restaurantMenu;

    @ManyToMany(mappedBy = "orderHistoryMenus")
    private Set<Order> orderHistoryOrders;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "menu_category",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> menuCategoryCategories;

}
