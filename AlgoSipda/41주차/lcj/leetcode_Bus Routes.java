import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        HashMap<Integer, List<Integer>> hmRoute = new HashMap<>();
        HashMap<Integer, Integer> hmDist = new HashMap<>();

        Queue<Integer> q = new ArrayDeque<>();
        HashSet<Integer> hs = new HashSet<>();

        for(int i=0;i<routes.length;i++) {
            for(int route: routes[i]) {
                hmRoute.putIfAbsent(route, new ArrayList<>());
                hmRoute.get(route).add(i);
            }
        }

        hmDist.put(source, 0);
        q.add(source);

        while(!q.isEmpty()) {
            int stop = q.poll();

            if (!hmRoute.containsKey(stop)) continue;
            
            for(int route: hmRoute.get(stop)) {
                if(!hs.contains(route)) {
                    hs.add(route);

                    for(int r :routes[route]) {
                        if(!hmDist.containsKey(r)) {
                            hmDist.put(r, hmDist.get(stop)+1);
                            q.offer(r);
                        }
                    }
                }
                
            }
        }

        return hmDist.getOrDefault(target, -1);
    }
}