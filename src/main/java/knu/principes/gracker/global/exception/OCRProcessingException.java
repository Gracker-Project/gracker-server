package knu.principes.gracker.global.exception;

public class OCRProcessingException extends RuntimeException {
    public OCRProcessingException(String message) {
        super(message);
    }

    public OCRProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
