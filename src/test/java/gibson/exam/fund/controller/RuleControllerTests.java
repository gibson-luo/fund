package gibson.exam.fund.controller;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RuleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_load_should_return_ok () throws Exception {

        String expected = "{\"maximumAmountPerDay\":\"5000\",\"maximumAmountPerWeek\":\"20000\",\"maximumLoadsPerDay\":\"3\"}";

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/rule/load"))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn();
    }
}
