import java.util.*;

class pro_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        int index = 0;
        int cnt = 0;
        int end = 0;
        while(cnt < jobs.length) {

            while(index < jobs.length && jobs[index][0] <= end) {
                pq.add(jobs[index++]);
            }

            if(pq.isEmpty()) {
                end = jobs[index][0];
            } else {
                int[] cur = pq.poll();
                answer += cur[1] + end - cur[0];
                end += cur[1];
                cnt++;
            }
        }
        return answer / jobs.length;
    }
}
