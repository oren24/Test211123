package org.oren.test211123.data;

public class Node {
    private Node left;
    private Node right;
    private String value;

    public Node(String value) {
        this(null, null, value);
    }

    public Node(Node left, Node right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }


    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
