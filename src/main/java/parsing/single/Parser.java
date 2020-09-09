package parsing.single;

public abstract class Parser {
    protected Lexer input;
    protected Token lookahead;

    public Parser(Lexer input) {
        this.input = input;
        consume();
    }

    public void match(int x) {
        if (lookahead.type == x)
            consume();
        else
            throw new Error("expecting " + input.getTokenName(x) + "; found " + lookahead);
    }

    public void consume() {
        lookahead = input.nextToken();
    }
}
