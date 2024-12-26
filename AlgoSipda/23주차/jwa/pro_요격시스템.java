import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        int N = targets.length;

        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        int x = 0;

        for (int i = 0; i < N; i++) {
            if (targets[i][0] < x)
                continue;
            x = targets[i][1];
            answer++;
        }

        return answer;
    }
}
