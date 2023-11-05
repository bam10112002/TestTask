package com.artem.analyzer;

import com.artem.analyzer.dtos.PairDTO;
import com.artem.analyzer.services.FrequencyService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FrequencyServiceTests {

    @Autowired
    FrequencyService frequencyService;

    @Test
    public void EmptyTest() {
        String request = "";
        var res = frequencyService.getFrequency(request);
        assertTrue(res.isEmpty());
    }
    @Test
    public void BaseTest() {
        String request = "aaaaabcccc";
        List<PairDTO> response = Arrays.asList(
                new PairDTO('a', 5L),
                new PairDTO('c', 4L),
                new PairDTO('b', 1L)
        );

        var res = frequencyService.getFrequency(request);
        assertIterableEquals(response, res);
    }

    @Test
    public void RussianCharacterTest() {
        String request = "ПППРР";
        List<PairDTO> response = Arrays.asList(
                new PairDTO('П', 3L),
                new PairDTO('Р', 2L)
        );

        var res = frequencyService.getFrequency(request);
        assertIterableEquals(response, res);
    }
}
