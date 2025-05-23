import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedBuses = new boolean[routes.length];

        if (!stopToBuses.containsKey(source)) return -1;
        for (int bus : stopToBuses.get(source)) {
            queue.offer(bus);
            visitedBuses[bus] = true;
        }

        int busesTaken = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int bus = queue.poll();
                for (int stop : routes[bus]) {
                    if (stop == target) return busesTaken;
                    if (visitedStops.contains(stop)) continue;
                    visitedStops.add(stop);
                    for (int nextBus : stopToBuses.getOrDefault(stop, new ArrayList<>())) {
                        if (!visitedBuses[nextBus]) {
                            visitedBuses[nextBus] = true;
                            queue.offer(nextBus);
                        }
                    }
                }
            }
            busesTaken++;
        }

        return -1;
    }
}