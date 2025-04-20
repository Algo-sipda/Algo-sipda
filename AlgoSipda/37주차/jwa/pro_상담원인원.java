import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        List<List<int[]>> typeList = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            typeList.add(new ArrayList<>());
        }

        for (int[] r : reqs) {
            int time = r[0];
            int len = r[1];
            int type = r[2];
            typeList.get(type).add(new int[] {time, len});
        }

        assign(k, n, new int[k], 0, 0, typeList);

        return min;
    }

    private void assign(int k, int n, int[] count, int i, int total, List<List<int[]>> typeList) {
        if (i == k) {
            if (total == n) {
                min = Math.min(min, waitTime(count, typeList));
            }
            return;
        }

        for (int x = 1; x <= n - total - (k - i - 1); x++) {
            count[i] = x;
            assign(k, n, count, i + 1, total + x, typeList);
        }
    }

    private int waitTime(int[] mentors, List<List<int[]>> typeList) {
        int sum = 0;

        for (int i = 0; i < mentors.length; i++) {
            int m = mentors[i];
            List<int[]> list = typeList.get(i + 1);

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int j = 0; j < m; j++)
                pq.offer(0);

            for (int[] r : list) {
                int start = r[0];
                int len = r[1];

                int end = pq.poll();
                if (end <= start) {
                    pq.offer(start + len);
                } else {
                    sum += (end - start);
                    pq.offer(end + len);
                }
            }
        }

        return sum;
    }
}
