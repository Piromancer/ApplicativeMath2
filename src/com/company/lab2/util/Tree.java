package com.company.lab2.util;

public class Tree implements Comparable<Tree>{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public Tree() {
        root = new Node();
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Tree(Node left, Node right) {
        root = new Node(left,right);
    }

    public Tree leftBranch(){
        return new Tree(root.leftChild);
    }

    public Tree rightBranch(){
        return new Tree(root.rightChild);
    }

    @Override
    public int compareTo(Tree o) {
        return Long.compare(root.frequency, o.root.frequency);
    }
}
