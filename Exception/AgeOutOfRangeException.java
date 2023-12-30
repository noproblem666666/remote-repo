package Exception;

public class AgeOutOfRangeException extends RuntimeException{
    public AgeOutOfRangeException() {
    }

    public AgeOutOfRangeException(String message) {
        super(message);
    }
}
