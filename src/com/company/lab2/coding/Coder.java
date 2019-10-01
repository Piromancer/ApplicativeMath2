package com.company.lab2.coding;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Coder {
    String POSSIBLE_SIMBOLS = IntStream.range('a','z').mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining())+'z';
    String OTHER_SYMBOLS = ".!?;:-\'\"()\\/{}#@";
    String ALL_SYMBOLS = POSSIBLE_SIMBOLS + OTHER_SYMBOLS;

    Map<Character, String> codesTable(String msg);
    String encryptMessage(String msg);
}
