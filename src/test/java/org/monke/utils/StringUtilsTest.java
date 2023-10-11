package org.monke.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    /* ------------------------------------------------------- */
    @Test
    public void isPalindrome_test() {
        assertThat(StringUtils.isPalindrome("ammo"), equalTo(false));
        assertThat(StringUtils.isPalindrome("anna"), equalTo(true));
    }

    /* ------------------------------------------------------- */
    @Test
    public void findLongestWord_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.findLongestWordRaw(null));

        assertThat(StringUtils.findLongestWordRaw("hello$@@world"), equalTo("hello"));
        assertThat(StringUtils.findLongestWordRaw("hello world"), equalTo("hello"));
        assertThat(StringUtils.findLongestWordRaw("hello worlds"), equalTo("worlds"));
        assertThat(StringUtils.findLongestWordRaw("hello @@ $worlds"), equalTo("worlds"));
        assertThat(StringUtils.findLongestWordRaw("hello again mr special$@@world"), equalTo("special"));

        assertThrows(NoSuchElementException.class, () -> StringUtils.findLongestWord(null));

        assertThat(StringUtils.findLongestWord("hello$@@world"), equalTo("hello"));
        assertThat(StringUtils.findLongestWord("hello world"), equalTo("hello"));
        assertThat(StringUtils.findLongestWord("hello worlds"), equalTo("worlds"));
        assertThat(StringUtils.findLongestWord("hello @@ $worlds"), equalTo("worlds"));
        assertThat(StringUtils.findLongestWord("hello again mr special$@@world"), equalTo("special"));
    }

    /* ------------------------------------------------------- */
    @Test
    public void longestWord_test() {
        assertThat(StringUtils.longestWordRaw(new String[]{"hello","world"}), equalTo("hello"));
        assertThat(StringUtils.longestWordRaw(new String[]{"hello","worlds"}), equalTo("worlds"));
        assertThat(StringUtils.longestWordRaw(new String[]{""}), equalTo(""));

        assertThat(StringUtils.longestWord(List.of("hello","world")), equalTo("hello"));
        assertThat(StringUtils.longestWord(List.of("hello","worlds")), equalTo("worlds"));
        assertThat(StringUtils.longestWord(List.of("")), equalTo(""));
    }

    /* ------------------------------------------------------- */
    @Test
    public void incrementAlphabet_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.incrementAlphabetRaw(null));

        assertThat(StringUtils.incrementAlphabetRaw("hello world"), equalTo("Ifmmp xpsmE"));
        assertThat(StringUtils.incrementAlphabetRaw("zZ45$"), equalTo("AA45$"));

        assertThrows(NoSuchElementException.class, () -> StringUtils.incrementAlphabet(null));

        assertThat(StringUtils.incrementAlphabet("hello world"), equalTo("Ifmmp xpsmE"));
        assertThat(StringUtils.incrementAlphabet("zZ45$"), equalTo("AA45$"));
    }

    /* ------------------------------------------------------- */
    @Test
    public void shiftLetterWithCase_test() {
        assertThat(StringUtils.shiftLetterWithCase("a"), equalTo("b"));
        assertThat(StringUtils.shiftLetterWithCase("z"), equalTo("a"));
        assertThat(StringUtils.shiftLetterWithCase("A"), equalTo("B"));
        assertThat(StringUtils.shiftLetterWithCase("Z"), equalTo("A"));
    }

    /* ------------------------------------------------------- */
    @Test
    public void containSpecialChars_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.containSpecialChars(null));

        assertThat(StringUtils.containSpecialChars("hello$@@world"), equalTo(true));
        assertThat(StringUtils.containSpecialChars("hello world"), equalTo(false));
    }

    /* ------------------------------------------------------- */
    @Test
    public void findSpecialChars_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.findSpecialCharsRaw(null));

        assertThat(StringUtils.findSpecialCharsRaw("hello$@@world"), equalTo("$@@"));
        assertThat(StringUtils.findSpecialCharsRaw("hello$@@@ù^$158"), equalTo("$@@ù^$")); //TODO char encoding
        assertThat(StringUtils.findSpecialCharsRaw("hello @@ $world"), equalTo("@@$"));
        assertThat(StringUtils.findSpecialCharsRaw("hello world"), equalTo(""));

        assertThrows(NoSuchElementException.class, () -> StringUtils.findSpecialChars(null));

        assertThat(StringUtils.findSpecialChars("hello$@@world"), equalTo("$@@"));
        assertThat(StringUtils.findSpecialCharsRaw("hello$@@@ù^$158"), equalTo("$@@ù^$")); //TODO char encoding
        assertThat(StringUtils.findSpecialChars("hello @@ $world"), equalTo("@@$"));
        assertThat(StringUtils.findSpecialChars("hello world"), equalTo(""));
    }

    /* ------------------------------------------------------- */
    @Test
    public void splitSpecialChars_test() {
        String[] resultRaw = new String[]{"hello","world"};
        List<String> result = List.of("hello","world");

        assertThat(StringUtils.splitSpecialCharsRaw("hello$@@world"), equalTo(resultRaw));
        assertThat(StringUtils.splitSpecialCharsRaw("hello $$ $  world"), equalTo(resultRaw));
        assertThat(StringUtils.splitSpecialCharsRaw("hello world"), equalTo(resultRaw));

        assertThat(StringUtils.splitSpecialChars("hello$@@world"), equalTo(result));
        assertThat(StringUtils.splitSpecialChars("hello $$ $  world"), equalTo(result));
        assertThat(StringUtils.splitSpecialChars("hello world"), equalTo(result));
    }

    /* ------------------------------------------------------- */
    @Test
    public void isALetter_test() {
        assertThat(StringUtils.isALetterRaw('h'), equalTo(true));
        assertThat(StringUtils.isALetterRaw('H'), equalTo(true));
        assertThat(StringUtils.isALetterRaw('7'), equalTo(false));

        assertThat(StringUtils.isALetter('h'), equalTo(true));
        assertThat(StringUtils.isALetter('H'), equalTo(true));
        assertThat(StringUtils.isALetter('7'), equalTo(false));
    }

    /* ------------------------------------------------------- */
    @Test
    public void isVowel_test() {
        assertThat(StringUtils.isVowel("a"), equalTo(true));
        assertThat(StringUtils.isVowel("b"), equalTo(false));
    }

    /* ------------------------------------------------------- */
    @Test
    public void removeWhitespace_test() {
        assertThat(StringUtils.removeWhitespace("aaa bbb"), equalTo("aaabbb"));
    }

    /* ------------------------------------------------------- */
    @Test
    public void getDotPositions_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.getDotPositions(null));
        assertThrows(NoSuchElementException.class, () -> StringUtils.getDotPositions(""));

        assertThat(StringUtils.getDotPositions("aaa"), equalTo(Collections.emptyList()));
        assertThat(StringUtils.getDotPositions("aaa.bbb.ccc."), equalTo(List.of(11,7,3)));
        assertThat(StringUtils.getDotPositions(".aaa.bbb.ccc"), equalTo(List.of(8,4,0)));
    }

    /* ------------------------------------------------------- */
    @Test
    public void parseString_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.parseString(null));
        assertThrows(NoSuchElementException.class, () -> StringUtils.parseString(""));

        assertThat(StringUtils.parseString("aaa,bbb,ccc,ddd"), equalTo(List.of("aaa","bbb","ccc","ddd")));
        assertThat(StringUtils.parseString(",aaa,bbb,ccc,ddd,"), equalTo(List.of("aaa","bbb","ccc","ddd")));
    }

    /* ------------------------------------------------------- */
    @Test
    public void findDuplicate_test() {
        assertThat(StringUtils.findDuplicates("simple test"), equalTo("est"));
        assertThat(StringUtils.findDuplicates("simple"), equalTo(""));
    }

    /* ------------------------------------------------------- */
    @Test
    public void isAnagram_test() {
        assertThat(StringUtils.isAnagramWord("bored", "robed"), equalTo(true));
        assertThat(StringUtils.isAnagramWord("bored", "boring"), equalTo(false));

        assertThat(StringUtils.isAnagram("bored", "robed"), equalTo(true));
        assertThat(StringUtils.isAnagram("bored$2845", "robed2485$"), equalTo(true));
        assertThat(StringUtils.isAnagram("bored$", "robed"), equalTo(false));
    }
}
