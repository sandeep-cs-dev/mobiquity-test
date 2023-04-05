package com.mobiquity.util;

import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.exception.InvalidItemException;
import com.mobiquity.model.Item;

import java.math.BigDecimal;

public class ItemValidation {
    public static void validateItem(Item item) throws InvalidItemException {
        validateItemCost(item.getCost());
        validateItemWeight(item.getWeight());
    }

    public static void validateItemWeight(BigDecimal itemWeight) throws InvalidItemException {
        if (itemWeight.signum() == -1) {
            throw new InvalidItemException(ExceptionMessage.NEGATIVE_ITEM_WEIGHT);
        }
        // check if Item weight is within allowed range
        if (itemWeight.compareTo(Constants.ITEM_MAX_WEIGHT) > 0) {
            throw new InvalidItemException(ExceptionMessage.INVALID_MAX_ITEM_WEIGHT);
        }
    }

    public static void validateItemCost(BigDecimal itemCost) throws InvalidItemException {
        // check if cost is negative
        if (itemCost.signum() == -1) {
            throw new InvalidItemException(ExceptionMessage.NEGATIVE_ITEM_COST);
        }
        // check if cost is within allowed range
        if (itemCost.compareTo(Constants.ITEM_MAX_COST) > 0) {
            throw new InvalidItemException(ExceptionMessage.ALLOWED_MAX_ITEM_COST);
        }
    }
}
