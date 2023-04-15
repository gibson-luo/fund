package gibson.exam.fund.tools;

import org.apache.tomcat.util.buf.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtil {

    public static List<LocalDate> getDaysOfWeek(LocalDate date) {
        List<LocalDate> days = new ArrayList<>();
        for(int i=1; i<=7; i++){
            LocalDate d = date.with(DayOfWeek.of(i));
            if(d.isAfter(LocalDate.now(ZoneOffset.UTC))){
                break;
            }else{
                days.add(d);
            }
        }
        return days;
    }

    public static String toInClauseString(List<LocalDate> days) {
        String result = StringUtils.join(days.stream().map(i -> "'" + i.toString() + "'").collect(Collectors.toList()), ',');
        return result;
    }

}
