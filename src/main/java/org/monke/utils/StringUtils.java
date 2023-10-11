package org.monke.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.monke.utils.Constants.*;

public class StringUtils {

    /**
     * Finds and return the longest word in a string.
     * If the word contains special characters, remove them and split the word.
     */
    public static String findLongestWordRaw(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        List<String> split = new ArrayList<>();
        List<String> specialCharsSplit = new ArrayList<>();

        if(containSpecialChars(input)) {
            specialCharsSplit = splitSpecialChars(input);
        }

        // Constructs the final Array.
        if(specialCharsSplit.isEmpty()) { // No special chars.
            split = Arrays.asList(input.split(" "));
        } else {
            for(String word : specialCharsSplit) {
                // trim to remove trailing spaces in case of an isolated special chars word (ex: " @@ hello").
                split.addAll( Arrays.asList(word.trim().split(" ")) );
            }
        }
        return longestWordRaw(split.toArray(new String[]{}));
    }

    /**
     * Finds and return the longest word in a string.
     * If the word contains special characters, remove them and split the word.
     */
    public static String findLongestWord(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        return longestWord(
                Stream.of(input)
                    .map(StringUtils::splitSpecialChars)
                    .flatMap(List::stream)
                    .map(s -> s.split(" "))
                    .map(Arrays::asList)
                    .flatMap(List::stream)
                    .map(String::trim)
                    .toList()
        );
    }

    /**
     * Returns the longest word in a list of strings.
     * @param words An array of strings with NO special characters and only letters.
     * @return The longest strings found (the first one if 2 or more of the same length).
     */
    public static String longestWordRaw(String[] words) {
        String result = "";

        for(String word : words) {
            if(word.length() > result.length()) { // > will return the first word found if they have the same length.
                result = word;
            }
        }
        return result;
    }

    /**
     * Returns the longest word in a list of strings.
     * @param words A list of strings with NO special characters and only letters.
     * @return The longest strings found (the first one if 2 or more of the same length).
     */
    public static String longestWord(List<String> words) {
        return words
                .stream()
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    /**
     * Returns a String :
     * <ul>
     *     <li>
     *         Letters are shifted by 1 in the alphabet.
     *     </li>
     *     <li>
     *         Numbers & special chars stays in place
     *     </li>
     *     <li>
     *         UpperCase is kept, vowels are returned in upper case.
     *     </li>
     * </ul>
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

    /**
     * Split a string by removing special chars.
     * @param input A string that may contain special characters.
     * @return An array of strings.
     */
    public static String[] splitSpecialCharsRaw(String input) {
        for(char specialChar : findSpecialChars(input).toCharArray()) {
            input = input.replace(specialChar, ' ');
        }
        String[] split = input.split(" ");
        List<String> result = new ArrayList<>();

        for(String word : split) {
            if(!word.isEmpty()) {
                result.add(word);
            }
        }
        return result.toArray(new String[]{});
    }

    /**
     * Split a string by removing special chars.
     * @param input A string that may contain special characters.
     * @return A list of strings.
     */
    public static List<String> splitSpecialChars(String input) {
        String specialChars = findSpecialCharsRaw(input);

        return Arrays.stream(
                    input.chars()
                        .mapToObj(c -> (char) c)
                        .map(c -> specialChars.contains("" + c) ? " " : "" + c)
                        .reduce("", (result, c) -> result + c)
                        .split(" ")
                )
                .filter(s -> !s.isEmpty())
                .toList();
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

    /** Return the index of a given character inside a string. */
    public static int findPosition(String input, String c) {
        return 0; //TODO
    }

    /** Returns a Stream of each character of a string, as a String. */
    public static Stream<String> streamString(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> "" + c);
    }

    /** Returns the index position of all dots in a string. */
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

    /** Converts a string of elements separated by commas into a List of strings. */
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
