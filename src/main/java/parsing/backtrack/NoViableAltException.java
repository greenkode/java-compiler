package parsing.backtrack;

public class NoViableAltException extends RecognitionException {
    public NoViableAltException(String s) {
        super(s);
    }
}
