package unit.tests.intro;

import java.time.LocalTime;

public class TimeUtility {
    public String getTimeOfDay() {
        var time = LocalTime.now();

        if (time.getHour() < 6) {
            return "Night";
        } else if (time.getHour() < 12) {
            return "Morning";
        } else if (time.getHour() < 18) {
            return "Afternoon";
        }
        return "Night";
    }
}