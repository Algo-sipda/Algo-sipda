//[leetcode] https://leetcode.com/problems/bus-routes
// 잘 모르겠음
import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        
        // 정류장 -> 버스 노선들 매핑 : why...?
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int r = 0; r < routes.length; r++) {
            for (int stop : routes[r]) {
                stopToRoutes.computeIfAbsent(stop, x -> new ArrayList<>()).add(r);
            }
        }

        // BFS 초기화
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();
        queue.add(source);
        visitedStops.add(source);
        int busCount = 0;

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int size = queue.size();
            busCount++;

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();
                List<Integer> routesList = stopToRoutes.getOrDefault(currentStop, new ArrayList<>());

                for (int route : routesList) {
                    if (visitedRoutes.contains(route)) continue;
                    visitedRoutes.add(route);

                    for (int nextStop : routes[route]) {
                        if (nextStop == target) {
                            return busCount;
                        }
                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.add(nextStop);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
