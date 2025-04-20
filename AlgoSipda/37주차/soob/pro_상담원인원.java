import java.util.*;

class Solution {
    static class Request {
        int start, time;
        Request(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        List<Request>[] typeList = new ArrayList[k];
        for (int i = 0; i < k; i++) typeList[i] = new ArrayList<>();

        for (int[] req : reqs) {
            int start = req[0];
            int time = req[1];
            int type = req[2] - 1;
            typeList[type].add(new Request(start, time));
        }

        int[][] wait = new int[k][n + 1];
        for (int i = 0; i < k; i++) {
            for (int c = 1; c <= n; c++) {
                wait[i][c] = getWaitTime(typeList[i], c);
            }
        }

        int[] assigned = new int[k];
        Arrays.fill(assigned, 1);
        int remain = n - k;

        while (remain-- > 0) {
            int idx = -1, max = -1;
            for (int i = 0; i < k; i++) {
                int cur = assigned[i];
                if (cur + 1 <= n && wait[i][cur] - wait[i][cur + 1] > max) {
                    max = wait[i][cur] - wait[i][cur + 1];
                    idx = i;
                }
            }
            assigned[idx]++;
        }

        int answer = 0;
        for (int i = 0; i < k; i++) {
            answer += wait[i][assigned[i]];
        }

        return answer;
    }

    int getWaitTime(List<Request> list, int cnt) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < cnt; i++) pq.add(0);

        int sum = 0;
        for (Request r : list) {
            int end = pq.poll();
            if (end > r.start) {
                sum += end - r.start;
                pq.add(end + r.time);
            } else {
                pq.add(r.start + r.time);
            }
        }
        return sum;
    }
}
