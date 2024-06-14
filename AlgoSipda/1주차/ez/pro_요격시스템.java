import java.util.*;

/*
    1. 끝나는 지점 기준 정렬
    2. for문 돌려서 기준점이 시작 위치보다 작으면 answer++
 */

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        int start = 0;
        for (int i = 0; i < targets.length; i++) {
            if (start <= targets[i][0]) {
                start = targets[i][1];
                answer++;
            }
        }
        return answer;
    }
}