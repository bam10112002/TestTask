package com.artem.analyzer.services;

import com.artem.analyzer.dtos.PairDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FrequencyService {

    /**
     * Метод подсчета частоты встречи символов в строке
     * @param str строка для анализа
     * @return список объектов (символ, количество встреч), отсортированный по убыванию
     */
    public List<PairDTO> getFrequency(@NonNull String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new PairDTO(entry.getKey(), entry.getValue()))
                .sorted()
                .toList();
    }
}
