import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<int[]> times = new ArrayList<>();

        for (String line : lines) {
            int[] timeRange = parseLog(line);
            times.add(timeRange);
        }

        int maxCount = 0;

        for (int[] time : times) {
            int endTime = time[1];
            int startTime = time[0];

            maxCount = Math.max(maxCount, countRequests(times, endTime, endTime + 999));
            maxCount = Math.max(maxCount, countRequests(times, startTime, startTime + 999));
        }

        return maxCount;
    }

    public int[] parseLog(String log) {
        String[] parts = log.split(" ");
        String[] timeParts = parts[1].split(":");

        int hours = Integer.parseInt(timeParts[0]) * 3600 * 1000;
        int minutes = Integer.parseInt(timeParts[1]) * 60 * 1000;
        int seconds = (int) (Double.parseDouble(timeParts[2]) * 1000);
        int endTime = hours + minutes + seconds;

        int duration = (int) (Double.parseDouble(parts[2].replace("s", "")) * 1000);
        int startTime = endTime - duration + 1;

        return new int[]{startTime, endTime};
    }

    public int countRequests(List<int[]> times, int start, int end) {
        int count = 0;
        for (int[] time : times) {
            if (time[1] >= start && time[0] <= end) 
                count++;
        }
        return count;
    }
}