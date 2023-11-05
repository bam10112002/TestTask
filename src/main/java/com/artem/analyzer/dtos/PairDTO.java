package com.artem.analyzer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PairDTO implements Comparable<PairDTO> {
    private Character symbol;
    private Long count;


    @Override
    public int compareTo(@NonNull PairDTO o) {
        return -Long.compare(this.count, o.count);
    }
}
