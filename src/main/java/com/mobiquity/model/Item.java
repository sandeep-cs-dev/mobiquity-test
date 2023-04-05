package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Item {
    private BigDecimal weight;
    private BigDecimal cost;
    private Integer index;
}
