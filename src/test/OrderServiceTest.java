package test;

import com.ordermanagement.model.Order;
import com.ordermanagement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService();
    }

    @Test
    void createOrder() {
        Order order = new Order("ORD123", 100.0, "John Doe");
        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        verify(orderRepository, times(1)).save(order);
        assert(createdOrder.getTotalAmount() == 100.0);
    }

    @Test
    void getOrderById() {
        Order order = new Order("ORD123", 100.0, "John Doe");
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

        Order fetchedOrder = orderService.getOrderById(1L);

        verify(orderRepository, times(1)).findById(1L);
        assert(fetchedOrder != null);
    }

    @Test
    void getOrderByIdNotFound() {
        when(orderRepository.findById(99L)).thenReturn(java.util.Optional.empty());

        try {
            orderService.getOrderById(99L);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Order not found"));
        }
    }
}

