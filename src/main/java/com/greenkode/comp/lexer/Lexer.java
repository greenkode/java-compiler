package com.greenkode.comp.lexer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Lexer {

    public static final char EMPTY_CHAR = ' ';
    public int line = 1;
    private char peek = EMPTY_CHAR;
    private final Map<String, Word> words = new HashMap<>();

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    public Token scan() throws IOException {
        for (; ; peek = (char) System.in.read()) {
            if (peek == '\n') line += 1;
            else break;
        }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                peek = (char) System.in.read();
            } while (Character.isDigit(peek));

            return new Num(v);
        }
        if (Character.isLetter(peek)) {
            StringBuilder b = new StringBuilder();
            do {
                b.append(peek);
                peek = (char) System.in.read();
            } while (Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word w = Optional.ofNullable(words.get(s)).orElse(new Word(Tag.ID, s));
            words.put(s, w);
            return w;
        }
        Token t = new Token(peek);
        peek = EMPTY_CHAR;
        return t;
    }

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }
}
