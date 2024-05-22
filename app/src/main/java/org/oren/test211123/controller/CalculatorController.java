package org.oren.test211123.controller;

import org.oren.test211123.data.Node;

public class CalculatorController {
    public Node buildTree(String expression) {
        // Verifying the current expression is a pure number (no operators)
        if (!expression.contains("+") &&
                !expression.contains("X") &&
                !expression.contains("/")) {
            return new Node(expression);
        }

        // Identifying the next operator to parse Priority : + --> - --> X --> /
        // Placing the X and / last, will cause them to be calculated first
        // in the calculation algorithm
        String signToParseWith = getSignToParseWith(expression);

        // Identifying the exact position of the operator (we only care for
        // the 1st instance of the operator)

        int signIndex = expression.indexOf(signToParseWith);


        String firstSubExpression = expression.substring(0, signIndex);

        String secondSubExpression = expression.substring(signIndex+1);

        // Building a subtree based on the 1st expression
        Node leftNode = buildTree(firstSubExpression);


        Node rightNode = buildTree(secondSubExpression);


        Node currentOperatorNode = new Node(leftNode, rightNode, signToParseWith);

        return currentOperatorNode;
    }


    private String getSignToParseWith(String expression) {
        if (expression.contains("+")) {
            return "+";
        }
//        if (expression.contains("-")) {
//            return  "-";
//        }

        if (expression.contains("X")) {
            return  "X";
        }
        if (expression.contains("/")) {
            return  "/";
        }
        return  "/";
    }

    public float calcAnswer(Node node) throws Exception {
        if (node == null) {
            return 0f;
        }

        // if leaf
        if (node.getRight() == null && node.getLeft()==null) {
            return Float.parseFloat(node.getValue());
        }

        float leftValue = calcAnswer(node.getLeft());
        float rightValue = calcAnswer(node.getRight());

        if (node.getValue().equals("X")) {
            return leftValue * rightValue;
        }

        if (node.getValue().equals("/")) {
            if (rightValue == 0){
                throw new Exception("Divide by zero");
            }
            return leftValue / rightValue;
        }

        if (node.getValue().equals("+")) {
            return leftValue + rightValue;
        }

        return leftValue - rightValue;
    }


}
