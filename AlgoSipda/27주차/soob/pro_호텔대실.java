import java.util.*;

class Solution {
        public int solution(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String[] time : book_time) {
            int start = convertToMinutes(time[0]);
            int end = convertToMinutes(time[1]) + 10;

            if (!pq.isEmpty() && pq.peek() <= start)
                pq.poll();

            pq.add(end);
        }

        return pq.size();
    }

    private int convertToMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}