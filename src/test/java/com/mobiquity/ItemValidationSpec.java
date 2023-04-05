package com.mobiquity;

import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.exception.InvalidItemException;
import com.mobiquity.model.Item;
import com.mobiquity.util.ItemValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ItemValidationSpec {

    @Test()
    void testForValidItem() {
        Item inputItem = new Item(BigDecimal.valueOf(88),
                BigDecimal.valueOf(56), 1);
        Assertions.assertDoesNotThrow(() -> {
            ItemValidation.validateItem(inputItem);
        });
    }

    @Test
    void testForNegativeItemCost() {
        InvalidItemException exception = Assertions.assertThrows(InvalidItemException.class, () -> {
            ItemValidation.validateItemCost(BigDecimal.valueOf(-2));
        });
        Assertions.assertEquals(ExceptionMessage.NEGATIVE_ITEM_COST, exception.getMessage());

    }

    @Test
    void testForMaxItemCost() {
        InvalidItemException exception = Assertions.assertThrows(InvalidItemException.class, () -> {
            ItemValidation.validateItemCost(BigDecimal.valueOf(101));
        });
        Assertions.assertEquals(ExceptionMessage.ALLOWED_MAX_ITEM_COST, exception.getMessage());

    }

    @Test
    void testForMaxItemWeight() {

        InvalidItemException exception = Assertions.assertThrows(InvalidItemException.class, () -> {
            ItemValidation.validateItemWeight(BigDecimal.valueOf(101));
        });
        Assertions.assertEquals(ExceptionMessage.INVALID_MAX_ITEM_WEIGHT, exception.getMessage());

    }

    @Test
    void testForNegativeItemWeight() {
        InvalidItemException exception = Assertions.assertThrows(InvalidItemException.class, () -> {
            ItemValidation.validateItemWeight(BigDecimal.valueOf(-101));
        });
        Assertions.assertEquals(ExceptionMessage.NEGATIVE_ITEM_WEIGHT, exception.getMessage());
    }
}
