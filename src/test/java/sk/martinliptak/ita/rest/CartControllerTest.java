package sk.martinliptak.ita.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sk.martinliptak.ita.model.CartDto;
import sk.martinliptak.ita.service.impl.CartServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.martinliptak.ita.mother.CartMother.prepareCartDto;
import static sk.martinliptak.ita.mother.CartMother.prepareCartDto1;

@WebMvcTest(CartController.class)
class CartControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartServiceImpl cartService;

    @Test
    void findCart() throws Exception {
        CartDto cartDto = prepareCartDto();
        when(cartService.findCart(1L)).thenReturn(cartDto);
        mockMvc.perform(get("/api/v1/carts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/carts/findCart.json")));

    }

    @Test
    void createCart() throws Exception {
        CartDto cartDto = prepareCartDto();
        when(cartService.createCart(1L)).thenReturn(cartDto);
        mockMvc.perform(post("/api/v1/carts/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/carts/createCart.json")));

    }

    @Test
    void addToCart() throws Exception {
        CartDto cartDto = prepareCartDto1();
        when(cartService.addToCart(1L, 2L)).thenReturn(cartDto);
        mockMvc.perform(put("/api/v1/carts/1/products/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent("/responses/carts/addToCart.json")));
    }
}