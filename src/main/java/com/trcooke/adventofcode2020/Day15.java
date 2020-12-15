package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay15Test");
        List<Integer> numbers = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            for (String number : line.split(",")) {
                numbers.add(Integer.parseInt(number));
            }
        }
        return getNumberSpoken(numbers, 2020);
    }

    Integer getNumberSpoken(List<Integer> nums, int numTurns) {
        Map<Integer, List<Integer>> history = new HashMap<>();
        Integer lastNum = -1;
        for (int i = 0; i < nums.size(); i++) {
            Integer num = nums.get(i);
            List<Integer> indexes = new ArrayList<>();
            indexes.add(i);
            history.put(num, indexes);
            lastNum = num;
        }

//        for num turns {
//            if last num not in history or in history only once {
//                next num is 0;
//                update history for 0;
//            } else num in history twice {
//                next num is diff between history indexes;
//                update history for diff between history
//            }
//            last num is next num
//        }
//        return last num;

        for (int i = nums.size(); i < numTurns; i++ ) {
            if (!history.containsKey(lastNum) || history.get(lastNum).size() == 1) {
                Integer nextNum = 0;
                updateHistory(history, nextNum, i);
                lastNum = nextNum;
            } else {
                List<Integer> indexes = history.get(lastNum);
                Integer nextNum = indexes.get(1) - indexes.get(0);
                updateHistory(history, nextNum, i);
                lastNum = nextNum;
            }
        }

        return lastNum;
//        List<Integer> numbers = new ArrayList<>(nums);
//        for (int i = numbers.size(); i < numTurns; i++) {
//            int lastNumberSpoken = numbers.get(i - 1);
//            if (!numbers.subList(0, numbers.size() - 1).contains(lastNumberSpoken)) {
//                numbers.add(0);
//            } else {
//                numbers.add(i - 1 - numbers.subList(0, i - 1).lastIndexOf(lastNumberSpoken));
//            }
//        }
//        return numbers.get(numbers.size() - 1);
    }

    private void updateHistory(Map<Integer, List<Integer>> history, Integer nextNum, int newIndex) {
        if (!history.containsKey(nextNum)) {
            List<Integer> newIndexes = new ArrayList<>();
            newIndexes.add(newIndex);
            history.put(nextNum, newIndexes);
        } else if (history.containsKey(nextNum) && history.get(nextNum).size() == 1) {
            history.get(nextNum).add(newIndex);
        } else {
            List<Integer> newIndexes = new ArrayList<>(history.get(nextNum).subList(1,2));
            newIndexes.add(newIndex);
            history.put(nextNum, newIndexes);
        }
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay15");
        List<Integer> numbers = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            for (String number : line.split(",")) {
                numbers.add(Integer.parseInt(number));
            }
        }
        return getNumberSpoken(numbers, 30000000);
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day15 solution = new Day15();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
