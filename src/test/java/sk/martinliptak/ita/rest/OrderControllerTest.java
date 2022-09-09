package sk.martinliptak.ita.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import sk.martinliptak.ita.domain.OrderStatus;
import sk.martinliptak.ita.model.OrderDto;
import sk.martinliptak.ita.service.impl.OrderServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.martinliptak.ita.mother.OrderMother.prepareOrderDto;

@WebMvcTest(OrderController.class)
class OrderControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderServiceImpl orderService;

    private final String adminAuthToken = "Basic YWRtaW46cGFzc3dvcmQ=";

    @Test
    void createOrder() throws Exception {
        OrderDto orderDto = prepareOrderDto();

        when(orderService.createOrder(1L)).thenReturn(orderDto);
        mockMvc.perform(post("/api/v1/orders/cart/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/orders/createOrder.json")));
    }

    @Test
    void findOrders() throws Exception {
        OrderDto orderDto = prepareOrderDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        when(orderService.findAllOrders()).thenReturn(List.of(orderDto));
        mockMvc.perform(get("/api/v1/orders")
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/orders/findOrders.json")));
    }

    @Test
    void updateOrderStatus() throws Exception {
        OrderDto orderDto = prepareOrderDto();
        orderDto.setStatus(OrderStatus.COMPLETED);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, adminAuthToken);
        when(orderService.updateStatus(1L, OrderStatus.COMPLETED)).thenReturn(orderDto);
        mockMvc.perform(put("/api/v1/orders/1/status/" + OrderStatus.COMPLETED)
                        .headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/orders/updateOrderStatus.json")));
    }
}