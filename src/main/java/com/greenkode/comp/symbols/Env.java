package com.greenkode.comp.symbols;

import java.util.HashMap;

public class Env {

    private final HashMap<String, Symbol> table;
    protected Env prev;

    public Env(Env prev) {
        table = new HashMap<>();
        this.prev = prev;
    }

    public void put(String s, Symbol symbol) {
        table.put(s, symbol);
    }

    public Symbol get(String s) {
        for (Env e = this; e != null; e = e.prev) {
            Symbol found = e.table.get(s);
            if (found != null) return found;
        }
        return null;
    }
}
