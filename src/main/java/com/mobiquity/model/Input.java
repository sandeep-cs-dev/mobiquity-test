package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Input {
    BigDecimal packageWeight;
    List<Item> items;
}
