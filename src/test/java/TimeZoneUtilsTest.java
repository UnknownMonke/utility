import org.junit.jupiter.api.Test;
import org.monke.TimeZoneUtils;

import java.text.ParseException;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TimeZoneUtilsTest {

    /* ------------------------------------------------------- */
    @Test
    public void findMonthToInt_test() throws ParseException {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 2);
        c.set(Calendar.MONTH, Calendar.MARCH);
        c.set(Calendar.YEAR, 2023);

        assertThat(TimeZoneUtils.findMonthToInt(c.getTime()), equalTo(3));
    }

    /* ------------------------------------------------------- */
    @Test
    public void findMonthYearToInt_test() throws ParseException {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 2);
        c.set(Calendar.MONTH, Calendar.MARCH);
        c.set(Calendar.YEAR, 2023);

        assertThat(TimeZoneUtils.findMonthYearToInt(c.getTime()), equalTo(new Integer[]{3, 2023}));
    }
}
