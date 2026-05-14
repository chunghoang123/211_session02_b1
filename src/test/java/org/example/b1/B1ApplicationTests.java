package org.example.b1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class B1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void getHotProductsReturnsJsonProductList() throws Exception {
        mockMvc.perform(get("/api/v1/products/hot"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("HP001"))
                .andExpect(jsonPath("$[0].name").value("\u00c1o thun 'Code is Life'"))
                .andExpect(jsonPath("$[0].price").value(199000.0))
                .andExpect(jsonPath("$[1].id").value("HP002"))
                .andExpect(jsonPath("$[1].name").value("M\u00f3c kh\u00f3a 'Bug Free'"))
                .andExpect(jsonPath("$[1].price").value(99000.0));
    }
}
