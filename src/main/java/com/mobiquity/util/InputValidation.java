package com.mobiquity.util;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.exception.InvalidInputException;
import com.mobiquity.exception.InvalidItemException;
import com.mobiquity.model.Item;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class contains logic to validate item details,
 * input format, input file and path
 *
 * */
public class InputValidation {
    // validate input line  to conform to below format
    // 81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3)
    public static void validateInputLineString(String line) throws InvalidInputException {
        Pattern p = Pattern.compile(Constants.INPUT_VALIDATION_REGEX);
        Matcher m = p.matcher(line);
        if (!m.matches()) {
            throw new InvalidInputException(ExceptionMessage.INVALID_INPUT_LINE);
        }
    }

    // validate if input package weight is within allowed limit
    public static void validPackage(BigDecimal inputCapacity) throws InvalidInputException {
        if (inputCapacity.compareTo(Constants.PACKAGE_ALLOWED_CAPACITY) > 0) {
            throw new InvalidInputException(ExceptionMessage.INVALID_PACKAGE_WEIGHT);
        }
    }

    public static void validateItemCount(Integer size) throws InvalidInputException {
        if (size > Constants.MAX_ITEM) {
            throw new InvalidInputException(ExceptionMessage.INVALID_TOTAL_ITEMS);
        }
    }

    public static void validateItem(Item item) throws InvalidItemException {
        ItemValidation.validateItem(item);
    }

    public static void isValidInputFilePath(String inputPath) throws APIException {
        Path path = Path.of(inputPath);
        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new InvalidInputException(ExceptionMessage.PATH_IS_A_DIRECTORY);
            } else if (!Files.isRegularFile(path)) {
                throw new InvalidInputException(ExceptionMessage.NOT_A_VALID_FILE);
            }
        } else {
            throw new InvalidInputException(ExceptionMessage.FILE_NOT_FOUND);
        }
    }
}
