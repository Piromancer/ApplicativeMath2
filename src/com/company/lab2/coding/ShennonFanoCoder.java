package com.company.lab2.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShennonFanoCoder implements Coder {

    private HashMap<Character, Long> dataCash = new HashMap<>();
    private HashMap<Character, Double> probabilities = new HashMap<>();
    private HashMap<Character, String> codes = new HashMap<>();
    private long totalSymbolsCount;

    private void resetCash(){
        ALL_SYMBOLS.chars().forEach(
                c -> {
                    dataCash.put((char) c, 0L);
                    codes.put((char) c, "");
                }
        );
    }

    private void gatherData(String msg){
        msg = msg.toLowerCase();
        resetCash();
        msg.chars()
                .filter(c -> ALL_SYMBOLS.indexOf(c) != -1)
                .forEach(c -> dataCash.compute((char) c, (k,v) -> v + 1));
        totalSymbolsCount = msg.length();
        dataCash.forEach(
                (k,v) -> probabilities.put(k, (double) v/totalSymbolsCount)
        );
    }

    private void fillCodes(int step, Map<Character,Double> currentPart){
        if(currentPart.size() <= 1) return;
        TreeMap<Character,Double> left = new TreeMap<>(), right = new TreeMap<>();
        if(currentPart.size() == 2){
            boolean flag = true;
            for(var i : currentPart.entrySet()){
                if(flag)codes.compute(i.getKey(), (k, v) -> v + '0');
                else codes.compute(i.getKey(),(k,v) -> v+'1');
                flag = false;
            }
            return;
        }
        char zeroOrOne = '0';
        double acc = 0;
        for(var i : currentPart.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList())){
            if (i.getValue() == 0) continue;
            if (acc >= (double) 1 / Math.pow(2, step+1))
                zeroOrOne = '1';
            acc += i.getValue();
            char finalZeroOrOne = zeroOrOne;
            codes.compute(i.getKey(), (k, v) -> v + finalZeroOrOne);
            if(zeroOrOne == '0')
                left.put(i.getKey(), i.getValue());
            else
                right.put(i.getKey(),i.getValue());
        }
        fillCodes(step+1, left);
        fillCodes(step+1, right);
    }

    @Override
    public Map<Character, String> codesTable(String msg) {
        gatherData(msg);
        TreeMap<Character, Double> sortedProbabilities = new TreeMap<>(probabilities);
        fillCodes(0, sortedProbabilities);
        return codes.entrySet().stream().filter(entry -> !entry.getValue().equals("")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String encryptMessage(String msg) {
        return null;
    }
}
