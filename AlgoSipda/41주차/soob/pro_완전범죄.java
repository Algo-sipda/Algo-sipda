import java.util.HashSet;
import java.util.Set;

class Solution {

    int end;
    int answer;
    int aMax, bMax;
    Set<String> visited;

    public int solution(int[][] info, int n, int m) {
        answer = Integer.MAX_VALUE;
        end = info.length;
        aMax = n;
        bMax = m;
        visited = new HashSet<>();
        DFS(0, 0, info, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void DFS(int a, int b, int[][] info, int idx) {
        if (a >= aMax || b >= bMax)
            return;

        if (idx == end) {
            answer = Math.min(a, answer);
            return;
        }

        String key = a + "," + b + "," + idx;
        if (visited.contains(key)) return;
        visited.add(key);

        DFS(a + info[idx][0], b, info, idx + 1);
        DFS(a, b + info[idx][1], info, idx + 1);
    }
}
