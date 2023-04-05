package com.mobiquity;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ExceptionMessage;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PackerTest {
    @Test()
    void testEmptyFile() {
        Path filePath = Paths.get("src", "test", "resources", "input_empty");
        String absoluteFilePath = filePath.toFile().getAbsolutePath();
        APIException exception = Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(absoluteFilePath);
        });
        Assertions.assertEquals(ExceptionMessage.EMPTY_FILE, exception.getMessage());
    }

    @Test()
    void testInvalidInputFormat() {
        Path filePath = Paths.get("src", "test", "resources", "invalid_input");
        String absoluteFilePath = filePath.toFile().getAbsolutePath();
        APIException exception = Assertions.assertThrows(APIException.class, () -> {
            Packer.pack(absoluteFilePath);
        });
        Assertions.assertEquals(ExceptionMessage.INVALID_INPUT_LINE, exception.getMessage());
    }

    @Test()
    void testInput1() {
        String newLine = System.getProperty("line.separator");
        Path resourceDirectory = Paths.get("src", "test", "resources", "example_input");
        final String EXPECTED_RESULT = String.join(newLine,
                "4", "-", "2,7", "8,9"
        );
        String absoluteFilePath = resourceDirectory.toFile().getAbsolutePath();
        try {
            String received = Packer.pack(absoluteFilePath);
            Assertions.assertEquals(EXPECTED_RESULT, received);
        } catch (APIException apiException) {
            Assertions.assertNull(apiException);
        }
    }

    @Test()
    void testInput2() {
        String newLine = System.getProperty("line.separator");
        Path resourceDirectory = Paths.get("src", "test", "resources", "example_input1");
        final String EXPECTED_RESULT = String.join(newLine,
                "2", "1,4"
        );
        String absoluteFilePath = resourceDirectory.toFile().getAbsolutePath();
        try {
            String received = Packer.pack(absoluteFilePath);
            Assertions.assertEquals(EXPECTED_RESULT, received);
        } catch (APIException apiException) {
            Assertions.assertNull(apiException);
        }
    }
}
