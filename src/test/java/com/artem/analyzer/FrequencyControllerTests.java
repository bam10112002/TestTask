package com.artem.analyzer;

import com.artem.analyzer.services.FrequencyService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class FrequencyControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FrequencyService frequencyService;

    @Test
    public void EmptyTest() {
        String request = "";
        var res = frequencyService.getFrequency(request);
        assertTrue(res.isEmpty());
    }
    @Test
    public void getFrequencyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency")
                        .param("data", "aaaaabcccc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("a"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].count").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("c"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].count").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].symbol").value("b"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].count").value(1));
    }
    @Test
    public void getFrequencyTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency")
                        .param("data", "pp\t\t\t "))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("\t"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].count").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("p"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].count").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].symbol").value(" "))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].count").value(1));
    }
    @Test
    public void getFrequencyWithRussianCharacterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency")
                        .param("data", "ппттт"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("т"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].count").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("п"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].count").value(2));
    }

    @Test
    public void getFrequencyWithUpperLowerCaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency")
                        .param("data", "ППттт"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol").value("т"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].count").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].symbol").value("П"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].count").value(2));
    }

    @Test
    public void EmptyFrequencyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency")
                        .param("data", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void BedRequestFrequencyTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/get/frequency"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
