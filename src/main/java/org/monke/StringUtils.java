package org.monke;

import java.util.*;
import java.util.stream.Collectors;

import static org.monke.Constants.*;

public class StringUtils {

    /**
     * Finds and return the longest word in a String.
     * If the word contains special characters, remove them and split the word.
     */
    public static String findLongestWord(String input) {
        return ""; //TODO
    }

    /**
     * Returns a String :
     * - Letters are shifted by 1 in the alphabet.
     * - Numbers & special chars stays in place
     * - UpperCase is kept, vowels are returned in upper case.
     */
    public static String incrementAlphabet(String input) {
        return ""; //TODO
    }

    public static boolean containSpecialChars(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        Scanner scanner = new Scanner(input);
        String specialCharacters = scanner.findInLine("[" + SPECIAL_CHARACTERS  + "]+");
        scanner.close();

        return !(specialCharacters == null || specialCharacters.isEmpty());
    }

    public static String findSpecialCharsRaw(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        char[] chars = input.toCharArray();
        StringBuilder result = new StringBuilder();

        for(char ch : chars) {
            if(SPECIAL_CHARACTERS.contains("" + ch)) {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String findSpecialChars(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        Scanner scanner = new Scanner(input);
        String specialCharacters = scanner.findInLine("[" + SPECIAL_CHARACTERS  + "]+");
        scanner.close();

        return specialCharacters == null ? "" : specialCharacters;
    }

    public static boolean isALetterRaw(char c) {
        for(char letter : ALPHABET.toCharArray()) {
            if(Character.toLowerCase(c) == letter) { // Ignore UpperCase.
                return true;
            }
        }
        return false;
    }

    public static boolean isALetter(char c) {
        Scanner scanner = new Scanner(String.valueOf(c));
        String letter = scanner.findInLine("[" + LETTERS  + "]+");
        scanner.close();

        return !(letter == null || letter.isEmpty());
    }

    /** Returns the index position of all dots in a String. */
    public static LinkedList<Integer> getDotPositions(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        LinkedList<Integer> dotPositions = new LinkedList<>();

        int i = input.length() - 1; // From the end.
        while (i > 0) {
            // Finds last dot.
            int j = input.lastIndexOf('.', i);
            // No last dot, returns.
            if(j < 0) {
                break;
            } else {
                dotPositions.add(j);
                i = j;
                i--;
            }
        }
        return dotPositions;
    }

    /** Converts a String of elements separated by commas into a List of Strings. */
    public static List<String> parseString(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        if(!input.contains(",")) {
            return Collections.emptyList();
        }
        List<String> list = Arrays.asList(input.split(","));

        return list
            .stream()
            .filter(s -> !"".equals(s))
            .collect(Collectors.toList());
    }
}
