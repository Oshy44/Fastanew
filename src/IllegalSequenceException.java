public class IllegalSequenceException extends RuntimeException {
    public IllegalSequenceException() {
    }

    public IllegalSequenceException(String Error) {
        super("Fehler in der Sequenz");
    }
}