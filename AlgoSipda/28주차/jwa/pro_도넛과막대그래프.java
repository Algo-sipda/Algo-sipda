import java.util.*;

class Solution {
    static final int NODE_SIZE = 1_000_001;
    static int edgeCount, nodeCount;
    static HashMap<Integer, ArrayList<Integer>> graph;
    static HashSet<Integer> visited;

    public int[] solution(int[][] edges) {
        int[] inCount = new int[NODE_SIZE];
        graph = new HashMap<>();
        visited = new HashSet<>();
        int newNode = 0;

        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }

            graph.get(edge[0]).add(edge[1]);
            inCount[edge[1]]++;
        }

        // 새 정점 찾기
        for (int i = 1; i < NODE_SIZE; i++) {
            if (!graph.containsKey(i))
                continue;
            if (inCount[i] == 0 && graph.get(i).size() > 1) {
                newNode = i;
                break;
            }
        }

        int donut = 0, stick = 0, eight = 0;

        for (int adj : graph.get(newNode)) {
            if (!graph.containsKey(adj)) {
                stick++;
                continue;
            }

            edgeCount = 0;
            nodeCount = 0;

            dfs(adj);

            if (edgeCount == nodeCount) {
                donut++;
            } else if (edgeCount == nodeCount - 1) {
                stick++;
            } else if (edgeCount == nodeCount + 1) {
                eight++;
            }
        }

        return new int[] {newNode, donut, stick, eight};
    }

    static void dfs(int n) {
        visited.add(n);
        nodeCount++;

        if (!graph.containsKey(n))
            return;

        for (int next : graph.get(n)) {
            edgeCount++;

            if (visited.contains(next)) {
                continue;
            }

            dfs(next);
        }
    }
}

// 새 정점은 들어오는 간선 없음, 나가는 간선이 2개 이상
// dfs 해서 간선, 노드 개수 카운트
// 도넛 -> 간선 개수 = n
// 막대 -> 간선 개수 = n - 1
// 팔자 -> 간선 개수 = n + 1
