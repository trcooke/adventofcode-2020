package com.trcooke.adventofcode2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Day04 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay04");
        Set<Field> requiredFields = new HashSet<>(Arrays.asList(
                Field.BYR,
                Field.IYR,
                Field.EYR,
                Field.HGT,
                Field.HCL,
                Field.ECL,
                Field.PID
        ));
        Set<Field> fieldNames = new HashSet<>();
        int valid = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                if (fieldNames.containsAll(requiredFields)) {
                    valid++;
                }
                fieldNames.clear();
            } else {
                String[] pairs = line.split(" ");
                for (String pair : pairs) {
                    fieldNames.add(Field.of(pair.split(":")[0]));
                }
            }
        }
        if (fieldNames.containsAll(requiredFields)) {
            valid++;
        }
        return valid;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay04");
        Set<Field> requiredFields = new HashSet<>(Arrays.asList(
                Field.BYR,
                Field.IYR,
                Field.EYR,
                Field.HGT,
                Field.HCL,
                Field.ECL,
                Field.PID
        ));
        Set<Field> validFields = new HashSet<>();
        int valid = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isEmpty()) {
                if (validFields.containsAll(requiredFields)) {
                    valid++;
                }
                validFields.clear();
            } else {
                String[] pairs = line.split(" ");
                for (String pair : pairs) {
                    Field field = Field.of(pair.split(":")[0]);
                    if (field.isValidValue(pair.split(":")[1])) {
                        validFields.add(field);
                    }
                }
            }
        }
        if (validFields.containsAll(requiredFields)) {
            valid++;
        }
        return valid;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    public static void main(String[] args) throws IOException {
        Day04 solution = new Day04();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private enum Field {
        BYR("byr", Field::byrValidation),
        IYR("iyr", Field::iyrValidation),
        EYR("eyr", Field::eyrValidation),
        HGT("hgt", Field::hgtValidation),
        HCL("hcl", Field::hclValidation),
        ECL("ecl", Field::eclValidation),
        PID("pid", Field::pidValidation),
        CID("cid", Field::cidValidation);

        private static boolean byrValidation(String value) {
            int val = Integer.parseInt(value);
            return val >= 1920 && val <= 2002;
        }

        private static Boolean iyrValidation(String value) {
            int val = Integer.parseInt(value);
            return val >= 2010 && val <= 2020;
        }

        private static Boolean eyrValidation(String value) {
            int val = Integer.parseInt(value);
            return val >= 2020 && val <= 2030;
        }

        private static Boolean hgtValidation(String value) {
            if (value.endsWith("in")) {
                int unitIndex = value.lastIndexOf("in");
                int hgtVal = Integer.parseInt(value.substring(0, unitIndex));
                return hgtVal >= 59 && hgtVal <= 76;
            }
            if (value.endsWith("cm")) {
                int unitIndex = value.lastIndexOf("cm");
                int hgtVal = Integer.parseInt(value.substring(0, unitIndex));
                return hgtVal >= 150 && hgtVal <= 193;
            }
            return false;
        }

        private static Boolean hclValidation(String value) {
            return Pattern.matches("#[0-9a-f]{6}", value);
        }

        private static Boolean eclValidation(String value) {
            Set<String> validEclVals = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
            return validEclVals.contains(value);
        }

        private static Boolean pidValidation(String value) {
            if (value.length() != 9) {
                return false;
            }
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }

        private static Boolean cidValidation(String value) {
            return true;
        }

        private final String fieldName;
        private final Function<String, Boolean> validation;

        Field(String fieldName, Function<String, Boolean> validation) {
            this.fieldName = fieldName;
            this.validation = validation;
        }

        public static Field of(String fieldName) {
            for (Field field : Field.values()) {
                if (field.getFieldName().equals(fieldName)) {
                    return field;
                }
            }
            throw new IllegalArgumentException("No field found");
        }

        public String getFieldName() {
            return fieldName;
        }

        public boolean isValidValue(String value) {
            return validation.apply(value);
        }
    }
}
