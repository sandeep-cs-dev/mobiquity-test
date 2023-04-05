package com.mobiquity.packer;

import com.mobiquity.model.Item;
import com.mobiquity.model.Output;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PackingSolutionImpl implements PackingSolution {
    public  Output findItemsToInclude(List<Item> items, BigDecimal capacity) {
        // Base case: if either the list of items or the capacity is empty, return 0
        if (items.isEmpty() || capacity.intValue() == 0) {
            return new Output(BigDecimal.valueOf(0), BigDecimal.valueOf(0), new ArrayList<>());
        }

        Item item = items.get(items.size() - 1);

        // If the weight of the item is greater than the capacity, skip this item and move on to the next item
        if (item.getWeight().compareTo(capacity) > 0) {
            return findItemsToInclude(items.subList(0, items.size() - 1), capacity);
        }

        // Calculate the cost and items selected if we include this item
        Output resultWithItem = findItemsToInclude(items.subList(0, items.size() - 1), capacity.subtract(item.getWeight()));
        BigDecimal costWithItem = resultWithItem.getMaxProfit().add(item.getCost());
        BigDecimal weightWithItem = resultWithItem.getMinWeight().add(item.getWeight());
        List<Item> itemsWithItem = new ArrayList<>(resultWithItem.getItems());
        itemsWithItem.add(item);

        // Calculate the cost and items selected if we exclude this item
        Output resultWithoutItem = findItemsToInclude(items.subList(0, items.size() - 1), capacity);
        // Return the maximum value and items selected between the two cases
        if (costWithItem.compareTo(resultWithoutItem.getMaxProfit()) > 0) {
            return new Output(costWithItem, weightWithItem, itemsWithItem);
        } else if (resultWithItem.getMaxProfit() != null && costWithItem.compareTo(resultWithoutItem.getMaxProfit()) == 0) {
            // in case both selection has equal profit then select item
            // with lower weight
            if (weightWithItem.compareTo(resultWithoutItem.getMinWeight()) < 0) {
                return new Output(costWithItem, weightWithItem, itemsWithItem);
            }
            return resultWithoutItem;
        } else {
            return resultWithoutItem;
        }
    }
}
