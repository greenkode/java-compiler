package parsing.backtrack;

public class BacktrackParser extends Parser {

    public BacktrackParser(Lexer input) {
        super(input);
    }

    public void stat() throws RecognitionException {
        if (speculative_stat_alt1()) {
            list();
            match(Lexer.EOF);
        } else if (speculative_stat_alt2()) {
            assign();
            match(Lexer.EOF);
        } else throw new NoViableAltException("expecting stat found " + LT(1));
    }

    private boolean speculative_stat_alt1() {
        boolean success = true;
        mark();
        try {
            list();
            match(Lexer.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        release();
        return success;
    }

    private boolean speculative_stat_alt2() {
        boolean success = true;
        mark();
        try {
            assign();
            match(Lexer.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        release();
        return success;
    }

    private void assign() throws RecognitionException {
        match(BacktrackLexer.LBRACK);
        elements();
        match(BacktrackLexer.RBRACK);
    }

    private void elements() throws RecognitionException {
        element();
        while (LA(1) == BacktrackLexer.COMMA) {
            match(BacktrackLexer.COMMA);
            elements();
        }
    }

    private void element() throws RecognitionException {
        if (LA(1) == BacktrackLexer.NAME && LA(2) == BacktrackLexer.EQUALS) {
            match(BacktrackLexer.NAME);
            match(BacktrackLexer.EQUALS);
            match(BacktrackLexer.NAME);
        } else if (LA(1) == BacktrackLexer.NAME) match(BacktrackLexer.NAME);
        else if (LA(1) == BacktrackLexer.LBRACK) list();
        else throw new NoViableAltException("expecting element, but found " + LT(1));
    }

    public void list() throws RecognitionException {
        match(BacktrackLexer.LBRACK);
    }
}
