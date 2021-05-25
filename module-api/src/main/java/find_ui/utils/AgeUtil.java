package find_ui.utils;

import java.time.LocalDateTime;
import java.time.Period;

public class AgeUtil {

    public static int getAge(LocalDateTime localDateTime) {
        return Period.between(localDateTime.toLocalDate(), LocalDateTime.now().toLocalDate()).getYears();
    }
}
