package com.mobiquity.util;

import com.mobiquity.model.Input;
import com.mobiquity.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParserImpl implements ItemParser {
    public Input parseLine(String inputLine) throws RuntimeException {
        // match input pattern 81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3)

        InputValidation.validateInputLineString(inputLine);
        String inputs[] = inputLine.trim().split(":");
        BigDecimal packageWeight = BigDecimal.valueOf(Double.valueOf(inputs[0]));

        // validate if package weight is within allowed limit;
        InputValidation.validPackage(packageWeight);
        List<Item> items = new ArrayList<>();
        Pattern itemPattern = Pattern.compile("\\((.*?)\\)");
        Matcher itemMatcher = itemPattern.matcher(inputs[1]);
        while (itemMatcher.find()) {
            String itemDetails[] = itemMatcher.group(1).split(",");
            Integer itemIndex = Integer.valueOf(itemDetails[0]);
            BigDecimal itemWeight = BigDecimal.valueOf(Double.valueOf(itemDetails[1]));
            String itemCostString = itemDetails[2].replaceAll("[^0-9]", "");
            BigDecimal itemCost = BigDecimal.valueOf(Double.valueOf(itemCostString));
            Item item = new Item(itemWeight, itemCost, itemIndex);
            InputValidation.validateItem(item);
            items.add(item);
        }
        // validate if line items count are not more than 15
        InputValidation.validateItemCount(items.size());
        return new Input(packageWeight, items);
    }
}
