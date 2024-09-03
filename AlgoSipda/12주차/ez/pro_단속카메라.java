import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, (o1,o2) -> (o1[1] - o2[1]));

        int max = routes[0][1];

        for(int i = 1; i < routes.length; i++) {
            if (routes[i][0] <= max && routes[i][1] >= max) {
                continue;
            } else {
                max = routes[i][1];
                answer++;
            }
        }

        return answer;
    }
}