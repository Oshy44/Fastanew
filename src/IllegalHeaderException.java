public class IllegalHeaderException extends RuntimeException {
    public IllegalHeaderException() {
    }

    public IllegalHeaderException(String Error) {
        super("Fehler im Header");
    }
}