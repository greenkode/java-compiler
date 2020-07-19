package com.greenkode.comp.parser;

import java.io.IOException;

class Parser {

    private int lookahead;

    public Parser() throws IOException {
        lookahead = System.in.read();
    }

    public void expr() throws IOException {
        term();
        while (true) {
            switch (lookahead) {
                case '+':
                    match('+');
                    term();
                    System.out.write('+');
                    break;
                case '-':
                    match('-');
                    term();
                    System.out.write('-');
                    break;
                default:
                    return;
            }
        }
    }

    private void term() throws IOException {
        if (Character.isDigit(lookahead)) {
            System.out.write(lookahead);
            match(lookahead);
        }
    }

    private void match(int t) throws IOException {
        if (lookahead == t) lookahead = System.in.read();
        else throw new SyntaxException("syntax error");
    }
}

class SyntaxException extends IOException {
    public SyntaxException(String message) {
        super(message);
    }
}

public class Postfix {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.expr();
        System.out.println('\n');
    }
}
