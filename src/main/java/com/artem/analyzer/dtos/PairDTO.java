package com.artem.analyzer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class PairDTO implements Comparable<PairDTO> {
    private Character ch;
    private Long numberOfOccurrences;


    @Override
    public int compareTo(@NonNull PairDTO o) {
        return -Long.compare(this.numberOfOccurrences, o.numberOfOccurrences);
    }
}
