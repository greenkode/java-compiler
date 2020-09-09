package parsing.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    Lexer input;
    private final Stack<Integer> markers;
    private final List<Token> lookahead;
    int p = 0;

    public Parser(Lexer input) {
        this.input = input;
        this.markers = new Stack<>();
        this.lookahead = new ArrayList<>();
        sync(1);
    }

    public Token LT(int i) {
        sync(i);
        return lookahead.get(p + i - 1);
    }

    public int LA(int i) {
        return LT(i).type;
    }

    public void match(int x) throws MismatchedTokenException {
        if (LA(1) == x) consume();
        else throw new MismatchedTokenException("expecting " + input.getTokenName(x) + " found " + LT(1));
    }

    public void sync(int i) {
        if (p + i - 1 > (lookahead.size() - 1)) {
            int n = (p + i - 1) - (lookahead.size() - 1);
            fill(n);
        }
    }

    public void fill(int n) {
        for (int i = 1; i <= n; i++) lookahead.add(input.nextToken());
    }

    public void consume() {
        p++;

        if (p == lookahead.size() && !isSpeculating()) {
            p = 0;
            lookahead.clear();
        }
        sync(1);
    }

    public int mark() {
        markers.add(p);
        return p;
    }

    public void release() {
        seek(markers.pop());
    }

    public void seek(int index) {
        p = index;
    }

    public boolean isSpeculating() {
        return !markers.isEmpty();
    }
}
