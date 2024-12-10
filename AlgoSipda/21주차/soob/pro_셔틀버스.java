import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> waitingTimes = new PriorityQueue<>();
        for (String time : timetable) {
            waitingTimes.add(toMinutes(time));
        }

        int busTime = 9 * 60;
        int lastPassengerTime = 0;

        for (int i = 0; i < n; i++) {
            int passengers = 0;
            while (!waitingTimes.isEmpty() && waitingTimes.peek() <= busTime && passengers < m) {
                lastPassengerTime = waitingTimes.poll() - 1;
                passengers++;
            }
            if (i == n - 1 && passengers < m) {
                lastPassengerTime = busTime;
            }
            busTime += t;
        }

        return toTimeString(lastPassengerTime);
    }

    private int toMinutes(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
    }

    private String toTimeString(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return padZero(hours) + ":" + padZero(mins);
    }

    private String padZero(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }
}
