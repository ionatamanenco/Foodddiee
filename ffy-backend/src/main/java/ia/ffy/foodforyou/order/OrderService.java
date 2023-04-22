package ia.ffy.foodforyou.order;

import ia.ffy.foodforyou.login.security.user.User;
import ia.ffy.foodforyou.login.security.user.UserRepository;
import ia.ffy.foodforyou.menu.Menu;
import ia.ffy.foodforyou.menu.MenuRepository;
import ia.ffy.foodforyou.util.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    public OrderService(final OrderRepository orderRepository, final UserRepository userRepository,
                        final MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final String id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final String id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final String id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setUserOrder(order.getUserOrder() == null ? null : order.getUserOrder().getId());
        orderDTO.setOrdersHistory(order.getOrderHistoryMenus() == null ? null : order.getOrderHistoryMenus().stream()
                .map(Menu::getId)
                .toList());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        final User userOrder = orderDTO.getUserOrder() == null ? null : userRepository.findById(orderDTO.getUserOrder())
                .orElseThrow(() -> new NotFoundException("User Order not found!"));
        order.setUserOrder(userOrder);
        final List<Menu> ordersHistory = menuRepository.findAllById(
                orderDTO.getOrdersHistory() == null ? Collections.emptyList() : orderDTO.getOrdersHistory());
        if (ordersHistory.size() != (orderDTO.getOrdersHistory() == null ? 0 : orderDTO.getOrdersHistory().size())) {
            throw new NotFoundException("One of menu history not found!");
        }
        order.setOrderHistoryMenus(new HashSet<>(ordersHistory));
        return order;
    }

}
