package parsing.backtrack;

public class MismatchedTokenException extends RecognitionException {
    public MismatchedTokenException(String s) {
        super(s);
    }
}
