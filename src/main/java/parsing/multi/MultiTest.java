package parsing.multi;

public class MultiTest {

    public static void main(String[] args) {
        Lexer lexer = new LookaheadLexer("[a,b=c,,[d,e]]");
        LookaheadParser parser = new LookaheadParser(lexer, 2);
        parser.list();
    }
}
