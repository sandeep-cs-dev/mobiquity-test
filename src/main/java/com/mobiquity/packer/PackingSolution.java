package com.mobiquity.packer;

import com.mobiquity.model.Item;
import com.mobiquity.model.Output;

import java.math.BigDecimal;
import java.util.List;

public interface PackingSolution {

    public Output findItemsToInclude(List<Item> items, BigDecimal capacity);
}
