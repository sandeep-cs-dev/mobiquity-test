package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Output {
    private BigDecimal maxProfit;
    private BigDecimal minWeight;
    private List<Item> items;

    /*
     * @return packed items indexes as string ie 1,2,3
     */
    public String toOutputString() {
        if (this.items.isEmpty()) {
            return "-";
        }
        String packedItemIndexes = items.stream()
                .map(item -> item.getIndex().toString())
                .collect(Collectors.joining(","));
        return packedItemIndexes;
    }
}
