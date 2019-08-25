package ru.otus.qa;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.qa.service.CalculatorService;
import ru.otus.qa.web.CalculatorController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ru.otus.qa.service.CalculatorService.class)
class CalculatorTest {
    
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CalculatorController controller = new CalculatorController(new CalculatorService());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testPlus() throws Exception{
        String result = mockMvc.perform(get("/calc?operation=plus&a=5&b=3"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("8", result);
    }
    @Test
    void testMinus() throws Exception{
        String result = mockMvc.perform(get("/calc?operation=minus&a=2&b=4"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("-2", result);
    }
    @Test
    void testMultiply() throws Exception{
        String result = mockMvc.perform(get("/calc?operation=multiply&a=165&b=-15"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("-2475", result);
    }
    @Test
    void testDivide() throws Exception{
        String result = mockMvc.perform(get("/calc?operation=divide&a=555&b=5"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("111", result);
    }
}
