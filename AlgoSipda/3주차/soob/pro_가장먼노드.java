import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int n, int[][] edge) {
        Map<Integer, ArrayList> map = new HashMap<>();

        for (int[] e : edge) {
            if (map.containsKey(e[0])) {
                ArrayList<Integer> tempList = map.get(e[0]);
                tempList.add(e[1]);
                map.put(e[0], tempList);
            } else {
                ArrayList<Integer> tempList = new ArrayList<>();
                tempList.add(e[1]);
                map.put(e[0], tempList);
            }

            if (map.containsKey(e[1])) {
                ArrayList<Integer> tempList = map.get(e[1]);
                tempList.add(e[0]);
                map.put(e[1], tempList);
            } else {
                ArrayList<Integer> tempList = new ArrayList<>();
                tempList.add(e[0]);
                map.put(e[1], tempList);
            }
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        cost[1] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (Object o : map.get(p.x)) {
                    int k = (int) o;
                    if (!visited[k] && cost[k] > p.y + 1) {
                        visited[k] = true;
                        cost[k] = p.y + 1;
                        queue.add(new Point(k, p.y + 1));
                    }
                }
            }
        }

        Arrays.sort(cost);
        int maxCost = cost[cost.length - 1];
        int answer = 0;
        for (int c : cost) {
            if (c == maxCost)
                answer++;
        }

        return answer;
    }
}