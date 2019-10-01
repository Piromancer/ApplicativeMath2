package com.company.lab2.util;

public class Node {
    public long frequency;
    public Character letter;
    public Node leftChild;
    public Node rightChild;

    public Node(){
        frequency = 0;
    }

    public Node(long frequency, char letter){
        this.frequency = frequency;
        this.letter = letter;
    }

    public Node(Node left, Node right){
        leftChild = left; rightChild = right;
        if(leftChild == null && rightChild == null) frequency = 0;
        else if(leftChild == null) frequency = rightChild.frequency;
        else if(rightChild == null) frequency = leftChild.frequency;
        else frequency = leftChild.frequency + rightChild.frequency;
    }

    public boolean isLetter(){
        return letter != null;
    }
}
