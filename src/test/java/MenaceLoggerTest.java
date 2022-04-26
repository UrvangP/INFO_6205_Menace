import org.junit.Test;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MenaceLoggerTest {
    @Test
    public void testLogMeCase() {
        MenaceLogger logger = new MenaceLogger();
        logger.logMe("Test");
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        String today = ((year > 9) ? Integer.toString(year) : "0" + Integer.toString(year)) + ((month > 9) ? Integer.toString(month) : "0" + Integer.toString(month)) + ((day > 9) ? Integer.toString(day) : "0" + Integer.toString(day));
        assertTrue(new File(path + "/application-" + today + ".log").isFile());
    }
}
