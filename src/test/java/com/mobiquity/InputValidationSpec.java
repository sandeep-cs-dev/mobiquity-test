package com.mobiquity;

import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.exception.InvalidInputException;
import com.mobiquity.util.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InputValidationSpec {
    @Test
    void invalidInputLine() {
        final String EXPECTED_MESSAGE = "Input line is invalid";
        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            InputValidation.validateInputLineString("test line");
        });
        Assertions.assertEquals(EXPECTED_MESSAGE, exception.getMessage());
    }

    @Test
    void validInputLine() {
        final String VALID_INPUT_LINE = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9)";
        Assertions.assertDoesNotThrow(() -> {
            InputValidation.validateInputLineString(VALID_INPUT_LINE);
        });
    }

    @Test
    void filePathDoesNotExist() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "test");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            InputValidation.isValidInputFilePath(absolutePath);
        });
        Assertions.assertEquals(ExceptionMessage.FILE_NOT_FOUND, exception.getMessage());
    }

    @Test
    void filePathIsADirectory() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            InputValidation.isValidInputFilePath(absolutePath);
        });
        Assertions.assertEquals(ExceptionMessage.PATH_IS_A_DIRECTORY, exception.getMessage());
    }
}