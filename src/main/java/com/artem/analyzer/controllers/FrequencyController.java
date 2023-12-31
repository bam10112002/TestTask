package com.artem.analyzer.controllers;

import com.artem.analyzer.dtos.PairDTO;
import com.artem.analyzer.services.FrequencyService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FrequencyController {

    private final FrequencyService frequencyService;

    @Autowired
    public FrequencyController(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    /**
     * Метод подсчета частоты встречи символов в строке
     * @param data строка для анализа
     * @return список объектов (символ, количество встреч), отсортированный по убыванию
     */
    @GetMapping("/get/frequency")
    public List<PairDTO> getFrequency(@NonNull @RequestParam String data) {
        return frequencyService.getFrequency(data);
    }
}
