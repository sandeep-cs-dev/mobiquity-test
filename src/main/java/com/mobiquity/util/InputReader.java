package com.mobiquity.util;

import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/*
 * Input Reader class Read input from file and
 * @author Sandeep Patel
 *
 */
public class InputReader {
    /*
     * @param Absolute file path as string
     * @return line as Stream
     */
    public static Stream<String> readInputFromFile(String filePath) throws APIException {
        try {
            InputValidation.isValidInputFilePath(filePath);
            return Files.lines(Path.of(filePath));
        } catch (IOException ioException) {
            throw new APIException(ioException.getMessage(), ioException);
        }
    }
}
