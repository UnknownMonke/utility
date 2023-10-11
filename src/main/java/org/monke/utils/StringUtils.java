package org.monke.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.monke.utils.Constants.*;

public class StringUtils {

    public static boolean isPalindrome(String input) {
        int start = 0;
        int end = input.length() - 1;

        do {
            if(input.charAt(start) != input.charAt(end)) {
                return false;
            }
            ++start;
            --end;
        } while(start <= input.length() / 2 && end >= input.length() / 2);

        return true;
    }

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
    public static String incrementAlphabetRaw(String input) {
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        StringBuilder result = new StringBuilder();

        for(char c : input.toCharArray()) {

            if(isALetter(c)) {
                String s = String.valueOf(c);
                s = shiftLetterWithCase(s);
                result.append(isVowel(s) ? s.toUpperCase() : s);
            } else {
                result.append(c);
            }
        }
        return result.toString();
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
        if(input == null || input.isEmpty()) { throw new NoSuchElementException(); }

        return streamString(input)
                .map(s -> isALetter(s.charAt(0)) ? shiftLetterWithCase(s) : s)
                .map(s -> isVowel(s) ? s.toUpperCase() : s)
                .reduce("", (result,s) -> result + s);
    }

    /** Returns the next letter in the alphabet. Conserves the case. */
    public static String shiftLetterWithCase(String letter) {
        boolean upper = letter.toUpperCase().equals(letter);

        int index = ALPHABET.indexOf(letter.toLowerCase()) + 1;
        if(index > 25) index = 0; // z loops to a.

        String result = String.valueOf(ALPHABET.charAt(index));

        return upper ? result.toUpperCase() : result;
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

    public static boolean isVowel(String s) {
        Scanner scanner = new Scanner(s);
        String letter = scanner.findInLine("[" + VOWELS  + "]+");
        scanner.close();

        return !(letter == null || letter.isEmpty());
    }

    /** Clever method for removing characters. */
    public static String removeWhitespace(String input) {
        return String.join("", input.split(" "));
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

    public static String findDuplicates(String input) {
        Set<String> unique = streamString(input)
                .collect(Collectors.toSet());

        return streamString(input)
                .map(s -> {
                    if(unique.contains(s)) {
                        unique.remove(s);
                        return "";
                    } else {
                        return s;
                    }
                })
                .reduce("", (r,s) -> r + s);
    }

    /**
     * Returns whether 2 strings contain exactly the same letters.
     * <br/>
     * Uses an efficient frequency count method.
     * @param s1 String containing only letters.
     * @param s2 String containing only letters.
     */
    public static boolean isAnagramWord(String s1, String s2) {
        return Arrays.equals(computeFrequencyWord(s1), computeFrequencyWord(s2));
    }

    public static Character[] computeFrequencyWord(String s) {
        Character[] freq = new Character[26];
        for(char c : s.toCharArray()) freq[ALPHABET.indexOf(c)] = c;
        return freq;
    }

    /** Returns whether 2 strings contain exactly the same set of characters. */
    public static boolean isAnagram(String s1, String s2) {
        return computeFrequency(s1).equals(computeFrequency(s2));
    }

    public static Map<Integer, Integer> computeFrequency(String s) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int c : s.chars().toArray()) {
            if(freq.containsKey(c)) freq.put(c, freq.get(c) + 1);
            else freq.put(c, 1);
        }
        return freq;
    }
}
