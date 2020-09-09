package parsing.single;

public class Test {

    public static void main(String[] args) {
        ListLexer lexer = new ListLexer("[a, b]");
        ListParser parser = new ListParser(lexer);
        parser.list();
    }
}
