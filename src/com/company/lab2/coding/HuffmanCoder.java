package com.company.lab2.coding;

import com.company.lab2.util.Node;
import com.company.lab2.util.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCoder implements Coder {

    private HashMap<Character, Long> dataCash = new HashMap<>();
    private HashMap<Character, String> codes = new HashMap<>();

    private void resetCash(){
        ALL_SYMBOLS.chars().forEach(
                c -> {
                    dataCash.put((char) c, 0L);
                }
        );
    }

    private void gatherData(String msg){
        msg = msg.toLowerCase();
        resetCash();
        msg.chars()
                .filter(c -> ALL_SYMBOLS.indexOf(c) != -1)
                .forEach(c -> dataCash.compute((char) c, (k,v) -> v + 1));
    }

    private void translateTree(Tree huffmanTree){
        class Recursion {
            void recursion(String code, Tree curTree) {
                if(curTree.getRoot().isLetter()) codes.put(curTree.getRoot().letter, code);
                else{
                    recursion(code+'0',curTree.leftBranch());
                    recursion(code+'1', curTree.rightBranch());
                }
            }
        }
        (new Recursion()).recursion("",huffmanTree);
    }

    @Override
    public Map<Character, String> codesTable(String msg) {
        resetCash();
        gatherData(msg);
        ArrayList<Tree> treesList = new ArrayList<>();
        dataCash.entrySet().stream().filter(entry -> entry.getValue()>0).forEach(
                entry -> {
                    treesList.add(new Tree(new Node(entry.getValue(),entry.getKey())));
                }
        );
        while(treesList.size()>1){
            treesList.sort(Tree::compareTo);
            Node fst = treesList.get(0).getRoot();
            Node snd = treesList.get(1).getRoot();
            treesList.remove(0);
            treesList.remove(0);
            treesList.add(new Tree(fst,snd));
        }
        translateTree(treesList.get(0));
        return codes;
    }

    @Override
    public String encryptMessage(String msg) {
        return null;
    }
}
