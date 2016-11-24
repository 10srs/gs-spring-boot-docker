package hello;

import java.util.Date;

public class Time {

    private String time = new Date().toString();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
