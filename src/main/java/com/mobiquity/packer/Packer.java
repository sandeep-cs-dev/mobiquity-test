package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.model.Input;
import com.mobiquity.model.Output;
import com.mobiquity.util.InputReader;
import com.mobiquity.util.ItemParser;
import com.mobiquity.util.ItemParserImpl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

    static private PackingSolution packingSolution;
    static private ItemParser itemParser;

    private Packer() {
    }

    static {
        packingSolution = new PackingSolutionImpl();
        itemParser = new ItemParserImpl();
    }

    /*
     * @param Input, items and package capacity
     * @return Output, packed items, cost and weight
     */
    private static Output packItems(Input inputItems) {
        return packingSolution.findItemsToInclude(inputItems.getItems(), inputItems.getPackageWeight());
    }

    public static String pack(String filePath) throws APIException {
        String outputString;
        try {
            String newLine = System.getProperty("line.separator");
            Stream<String> input = InputReader.readInputFromFile(filePath);
            outputString = input.map((line) -> {
                Input inputItems = itemParser.parseLine(line);
                Output packedItems = packItems(inputItems);
                return packedItems.toOutputString();
            }).collect(Collectors.joining(newLine));
        } catch (RuntimeException exception) {
            throw new APIException(exception.getMessage(), exception);
        }
        if (outputString.isEmpty()) {
            throw new APIException(ExceptionMessage.EMPTY_FILE);
        }
        return outputString;
    }
}

