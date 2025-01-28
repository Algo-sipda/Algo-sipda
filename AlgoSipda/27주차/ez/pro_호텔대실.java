import java.util.*;

public class pro_호텔대실 {

    static class Time implements Comparable<Time>{
        int start, end;
        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Time o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public int solution(String[][] book_time) {
        PriorityQueue<Time> pq = new PriorityQueue<>();
        for (String[] time : book_time) {
            int start = Integer.parseInt(time[0].split(":")[0] + time[0].split(":")[1]);
            int end = Integer.parseInt(time[1].split(":")[0] + time[1].split(":")[1]);
            pq.add(new Time(start, end));
        }

        List<Integer> endTimeList = new ArrayList<>();

        while (!pq.isEmpty()) {
            Time cur = pq.poll();
            if (endTimeList.size() == 0) {
                endTimeList.add(cur.end);
            } else {
                Collections.sort(endTimeList);
                boolean flag = false;
                for (int i = 0; i < endTimeList.size(); i++) {
                    int next = endTimeList.get(i) + 10;
                    if (next % 100 > 60) {
                        next -= 60;
                        next += 100;
                    }
                    if (next <= cur.start) {
                        flag = true;
                        endTimeList.set(i, cur.end);
                        break;
                    }
                }
                if (!flag) {
                    endTimeList.add(cur.end);
                }
            }
        }

        return endTimeList.size();
    }
}
