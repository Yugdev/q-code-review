import com.ordermanagement.model.Order;
import com.ordermanagement.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createOrder() {
        Order order = new Order("ORD123", 100.0, "John Doe");
        when(orderService.createOrder(order)).thenReturn(order);

        Order createdOrder = orderController.createOrder(order);

        verify(orderService, times(1)).createOrder(order);
        assert(createdOrder.getOrderNumber().equals("ORD123"));
    }
}

