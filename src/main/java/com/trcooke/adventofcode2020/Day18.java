package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day18 {

    long part1() throws IOException {
        BufferedReader reader = getInput("InputDay18");
        List<String> expressions = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            expressions.add(line);
        }
        long sumOfExpressions = 0;
        for (String expression : expressions) {
            long result = evaluate(expression);
            System.out.println(result);
            sumOfExpressions += result;
            System.out.println(sumOfExpressions);
        }

        return sumOfExpressions;
    }

    long evaluate(String expression) {
        expression = expression.replace(" ", "");
        System.out.println();
        long acc = 0;
        return evaluateIter(expression, acc);
    }

    private long evaluateIter(String expression, long acc) {
        System.out.println(expression);
        char operation = '?';
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if (c == '(') {
                int openParenCount = 1;
                int nextCharIndex = i + 1;
                char nextChar;
                while (expression.charAt(nextCharIndex) != ')' || openParenCount != 1) {
                    nextChar = expression.charAt(nextCharIndex);
                    if (nextChar == '(') {
                        openParenCount += 1;
                    } else if (nextChar == ')') {
                        openParenCount -= 1;
                    }
                    nextCharIndex++;
                }
                switch (operation) {
                    case '+':
                        acc += evaluateIter(expression.substring(i + 1, nextCharIndex), acc);
                        break;
                    case '*':
                        acc *= evaluateIter(expression.substring(i + 1, nextCharIndex), acc);
                        break;
                    default: acc = evaluateIter(expression.substring(i + 1, nextCharIndex), acc);
                }
                i = nextCharIndex;
            } else if (c == '+' || c == '*') {
                operation = c;
            } else if (operation == '?') {
                acc = Integer.parseInt(String.valueOf(c));
            } else {
                switch (operation) {
                    case '+':
                        acc += Integer.parseInt(String.valueOf(c));
                        break;
                    case '*':
                        acc *= Integer.parseInt(String.valueOf(c));
                        break;
                }
            }
            i++;
        }
        return acc;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay18Test1");
        for (String line; (line = reader.readLine()) != null;) {
        }
        return 0;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day18 solution = new Day18();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
