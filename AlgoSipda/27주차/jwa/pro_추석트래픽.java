import java.util.*;

class Solution {
    static class Traffic {
        int startTime;
        int endTime;

        Traffic(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public int solution(String[] lines) {
        List<Traffic> traffics = new ArrayList<>();
        for (String line : lines) {
            String[] splits = line.split(" ");
            int end = convertToMs(splits[1]);
            int start = end - (int) (Double.parseDouble(splits[2].replace("s", "")) * 1000) + 1;

            Traffic traffic = new Traffic(start, end);
            traffics.add(traffic);
        }

        int answer = 1;

        for (int i = 0; i < traffics.size(); i++) {
            int cnt = 0;
            int end = traffics.get(i).endTime;

            for (Traffic job : traffics) {
                if (job.startTime < end + 1000 && job.endTime >= end)
                    cnt++;
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);

        return answer;
    }

    public int convertToMs(String time) {
        int t = 0;
        String[] times = time.split(":");

        t += (Long.parseLong(times[0]) * 60 * 60);
        t += (Long.parseLong(times[1]) * 60);
        t *= 1000;
        t += (Double.parseDouble(times[2]) * 1000);

        return t;
    }
}
