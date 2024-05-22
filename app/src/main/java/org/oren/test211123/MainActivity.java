package org.oren.test211123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.oren.test211123.controller.CalculatorController;
import org.oren.test211123.data.Node;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.zero).setOnClickListener(new buttonListener());
        findViewById(R.id.one).setOnClickListener(new buttonListener());
        findViewById(R.id.two).setOnClickListener(new buttonListener());
        findViewById(R.id.three).setOnClickListener(new buttonListener());
        findViewById(R.id.four).setOnClickListener(new buttonListener());
        findViewById(R.id.five).setOnClickListener(new buttonListener());
        findViewById(R.id.six).setOnClickListener(new buttonListener());
        findViewById(R.id.seven).setOnClickListener(new buttonListener());
        findViewById(R.id.eight).setOnClickListener(new buttonListener());
        findViewById(R.id.nine).setOnClickListener(new buttonListener());
        findViewById(R.id.plus).setOnClickListener(new buttonListener());
        findViewById(R.id.minus).setOnClickListener(new buttonListener());
        findViewById(R.id.multiply).setOnClickListener(new buttonListener());
        findViewById(R.id.divide).setOnClickListener(new buttonListener());
        findViewById(R.id.answer).setOnClickListener(new buttonListener());
        findViewById(R.id.clear).setOnClickListener(new buttonListener());
        findViewById(R.id.clearall).setOnClickListener(new buttonListener());
        findViewById(R.id.dot).setOnClickListener(new buttonListener());

    }

    private class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String text = ((TextView) findViewById(view.getId())).getText().toString();
            String currentExpression = ((TextView) findViewById(R.id.headline)).getText().toString();
            if (view.getId() == R.id.clearall)
                currentExpression = "";
            else if (view.getId() == R.id.clear)
                currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            else if (view.getId() == R.id.answer) {
                ((TextView) findViewById(R.id.history)).setText(currentExpression);
                CalculatorController calculatorController = new CalculatorController();
                try {
                    Node node = calculatorController.buildTree(currentExpression);
                    float answer = calculatorController.calcAnswer(node);
                    currentExpression = String.valueOf(answer);
                } catch (Exception e) {
                    if (e.getMessage().equals("Divide by zero")) {

                        currentExpression = "Error: can not divide zero (limit"+"-> ±" + "\u221E" + ")";
                    } else {
                        currentExpression = "Error: Invalid expression";
                    }

                }
            } else if (view.getId() == R.id.dot) {
                if (currentExpression.length() == 0)
                    currentExpression = "0.";
                else if (currentExpression.charAt(currentExpression.length() - 1) == '.') {
                } else if (currentExpression.charAt(currentExpression.length() - 1) == '+' || currentExpression.charAt(currentExpression.length() - 1) == '-' || currentExpression.charAt(currentExpression.length() - 1) == 'X' || currentExpression.charAt(currentExpression.length() - 1) == '/')
                    currentExpression = currentExpression + "0.";
                else
                    currentExpression = currentExpression + ".";

            } else {
                currentExpression = currentExpression + text;
            }
            ((TextView) findViewById(R.id.headline)).setText(currentExpression);
        }
    }
}