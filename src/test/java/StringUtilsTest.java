import org.junit.jupiter.api.Test;
import org.monke.StringUtils;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    /* ------------------------------------------------------- */
    @Test
    public void containSpecialChars_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.containSpecialChars(null));
        assertThat(StringUtils.containSpecialChars("hello$@@world"), equalTo(true));
        assertThat(StringUtils.containSpecialChars("hello world"), equalTo(false));

    }

    /* ------------------------------------------------------- */
    @Test
    public void findSpecialCharsRaw_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.findSpecialCharsRaw(null));
        assertThat(StringUtils.findSpecialCharsRaw("hello$@@world"), equalTo("$@@"));
        assertThat(StringUtils.findSpecialCharsRaw("hello$@@@ù^$158"), equalTo("$@@ù^$")); //TODO char encoding
        assertThat(StringUtils.findSpecialCharsRaw("hello world"), equalTo(""));

    }

    /* ------------------------------------------------------- */
    @Test
    public void findSpecialChars_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.findSpecialChars(null));
        assertThat(StringUtils.findSpecialChars("hello$@@world"), equalTo("$@@"));
        assertThat(StringUtils.findSpecialChars("hello world"), equalTo(""));

    }

    /* ------------------------------------------------------- */
    @Test
    public void isALetterRaw_test() {
        assertThat(StringUtils.isALetterRaw('h'), equalTo(true));
        assertThat(StringUtils.isALetterRaw('H'), equalTo(true));
        assertThat(StringUtils.isALetterRaw('7'), equalTo(false));

    }

    /* ------------------------------------------------------- */
    @Test
    public void isALetter_test() {
        assertThat(StringUtils.isALetter('h'), equalTo(true));
        assertThat(StringUtils.isALetter('H'), equalTo(true));
        assertThat(StringUtils.isALetter('7'), equalTo(false));

    }

    /* ------------------------------------------------------- */
    @Test
    public void getDotPositions_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.getDotPositions(null));
        assertThrows(NoSuchElementException.class, () -> StringUtils.getDotPositions(""));

        assertThat(StringUtils.getDotPositions("aaa"), equalTo(Collections.emptyList()));

        LinkedList<Integer> result1 = new LinkedList<>();
        result1.add(11);
        result1.add(7);
        result1.add(3);

        LinkedList<Integer> result2 = new LinkedList<>();
        result2.add(8);
        result2.add(4);
        result2.add(0);

        assertThat(StringUtils.getDotPositions("aaa.bbb.ccc."), equalTo(result1));
        assertThat(StringUtils.getDotPositions(".aaa.bbb.ccc"), equalTo(result2));
    }

    /* ------------------------------------------------------- */
    @Test
    public void parseString_test() {
        assertThrows(NoSuchElementException.class, () -> StringUtils.parseString(null));
        assertThrows(NoSuchElementException.class, () -> StringUtils.parseString(""));

        List<String> result = new ArrayList<>();
        result.add("aaa");
        result.add("bbb");
        result.add("ccc");
        result.add("ddd");

        assertThat(StringUtils.parseString("aaa,bbb,ccc,ddd"), equalTo(result));
        assertThat(StringUtils.parseString(",aaa,bbb,ccc,ddd,"), equalTo(result));
    }
}
