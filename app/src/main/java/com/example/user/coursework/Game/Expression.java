package com.example.user.coursework.Game;

import java.util.Random;

/**
 * Created by User on 07.03.2015.
 */
public class Expression {

    private int complexity;//колво операций в выражении
    private String expression="";
    private int expressionAnswer;


    public String getExpression() {
        return expression;
    }

    public int getExpressionAnswer() {
        return expressionAnswer;
    }

    public Expression(int complexity) {

        this.complexity = complexity;
        generation();

    }

    private void generation() {
        Random rand = new Random();
        int i = complexity;
        int first = rand.nextInt(9) + 1;
        int sum = first;
        expression += sum;
        while (i != 0) {
            int operation = rand.nextInt(2);
            if (operation == 0) {
                int temp = rand.nextInt(9) + 1;
                expression += " " + "+" + " " + temp;
                sum += temp;
            }
            if (operation == 1) {
                int temp = rand.nextInt(9) + 1;
                expression += " " + "-" + " " + temp;
                sum -= temp;
            }

            i--;

        }
        if(sum<0){
           sum=0;
            expression="";
            generation();
        }else{
            expressionAnswer = sum;
        }


    }





}
