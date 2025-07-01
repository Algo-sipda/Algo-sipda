import java.util.*;

class pro_야근지수 {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pq.add(work);
        }

        while (n > 0) {
            int work = pq.poll();
            if (work == 0) {
                return 0;
            }

            pq.add(work - 1);
            n--;
        }

        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
