import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        Map<Integer, List<Integer>> winMap = new HashMap<>();
        Map<Integer, List<Integer>> loseMap = new HashMap<>();
        for (int[] result : results) {
            winMap.computeIfAbsent(result[0], k -> new ArrayList<>()).add(result[1]);
            loseMap.computeIfAbsent(result[1], k -> new ArrayList<>()).add(result[0]);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            Queue<Integer> winQueue = new LinkedList<>();
            winQueue.add(i);
            Set<Integer> winSet = new HashSet<>();
            while (!winQueue.isEmpty()) {
                int now = winQueue.poll();
                if (winMap.get(now) == null) continue;
                for (int x : winMap.get(now)) {
                    if (!winSet.contains(x)) {
                        winSet.add(x);
                        winQueue.add(x);
                    }
                }
            }
            Queue<Integer> loseQueue = new LinkedList<>();
            loseQueue.add(i);
            Set<Integer> loseSet = new HashSet<>();
            while (!loseQueue.isEmpty()) {
                int now = loseQueue.poll();
                if (loseMap.get(now) == null) continue;
                for (int x : loseMap.get(now)) {
                    if (!loseSet.contains(x)) {
                        loseSet.add(x);
                        loseQueue.add(x);
                    }
                }
            }
            if (winSet.size() + loseSet.size() == n - 1)
                answer++;
        }
        return answer;
    }
}