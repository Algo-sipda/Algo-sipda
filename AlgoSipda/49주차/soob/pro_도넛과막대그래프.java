import java.util.*;

class Solution {
    
    public int[] parents;

    public void init(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    public int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y)
            parents[y] = x;
        else
            parents[x] = y;
    }
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        Map<Integer, Integer> inDegree = new HashMap<>(); 
        Map<Integer, Integer> outDegree = new HashMap<>();
        
        int maxKey = 0;
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            maxKey = Math.max(maxKey, Math.max(a, b));
            outDegree.put(a, outDegree.getOrDefault(a, 0) + 1);
            inDegree.put(b, inDegree.getOrDefault(b, 0) + 1);
        }

        int added = 0;
        for (int i = 1; i <= maxKey; i++) {
            if(!outDegree.containsKey(i))
                continue;
            if (!inDegree.containsKey(i) && outDegree.get(i) >= 2) {
                added = i;
                break;
            }
        }
        
        answer[0] = added;

        init(maxKey);
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (a != added) {
                union(a, b);
            }
        }

        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int i = 1; i <= maxKey; i++) {
            if (i == added) continue;
            int root = find(i);
            groupMap.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> group : groupMap.values()) {
            if(group.size() == 1 && !outDegree.containsKey(group.get(0)) && !inDegree.containsKey(group.get(0)))
                continue;
            int nodeCnt = group.size();
            int edgeCnt = 0;
            for (int node : group) {
                edgeCnt += outDegree.getOrDefault(node, 0);
            }

            if (nodeCnt == edgeCnt) {
                answer[1]++;
            } else if (nodeCnt == edgeCnt + 1) {
                answer[2]++;
            } else {
                answer[3]++;
            }
        }

        return answer;
    }
}
