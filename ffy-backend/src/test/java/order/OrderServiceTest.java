package order;

import ia.ffy.foodforyou.login.security.user.UserRepository;
import ia.ffy.foodforyou.menu.MenuRepository;
import ia.ffy.foodforyou.order.Order;
import ia.ffy.foodforyou.order.OrderDTO;
import ia.ffy.foodforyou.order.OrderRepository;
import ia.ffy.foodforyou.order.OrderService;
import ia.ffy.foodforyou.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfOrders() {
        // Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        when(orderRepository.findAll((Example<Order>) any())).thenReturn(Arrays.asList(order1, order2));

        // Act
        List<OrderDTO> result = orderService.findAll();

        // Assert
        verify(orderRepository).findAll((Example<Order>) any());
        assertEquals(2, result.size());
    }

    @Test
    void get_WithExistingOrderId_ShouldReturnOrder() {
        // Arrange
        Order order = new Order();
        order.setId("1");
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));

        // Act
        OrderDTO result = orderService.get("1");

        // Assert
        verify(orderRepository).findById("1");
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void get_WithNonExistingOrderId_ShouldThrowNotFoundException() {
        // Arrange
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        // Assert
        assertThrows(NotFoundException.class, () -> orderService.get("1"));
    }

    @Test
    void create_ShouldSaveOrderAndReturnOrderId() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        Order order = new Order();
        when(orderRepository.save(any())).thenReturn(order);

        // Act
        String result = orderService.create(orderDTO);

        // Assert
        verify(orderRepository).save(any());
        assertEquals(order.getId(), result);
    }

    @Test
    void update_WithExistingOrderId_ShouldUpdateOrder() {
        // Arrange
        Order order = new Order();
        order.setId("1");
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId("1");
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(menuRepository.findAllById(any())).thenReturn(Collections.emptyList());

        // Act
        orderService.update("1", orderDTO);

        // Assert
        verify(orderRepository).findById("1");
        verify(orderRepository).save(order);
    }

    @Test
    void update_WithNonExistingOrderId_ShouldThrowNotFoundException() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        // Assert
        assertThrows(NotFoundException.class, () -> orderService.update("1", orderDTO));
    }

    @Test
    void delete_WithExistingOrderId_ShouldDeleteOrder() {
        // Act
        orderService.delete("1");

        // Assert
        verify(orderRepository).deleteById("1");
    }
}
