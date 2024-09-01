import java.util.*;

/*
    0번: 진입한 지점
    1번: 고속도로 나간 지점속도로 나간 지점
*/

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1]-o2[1]);
        
        int answer = 1;
        int curLocation = routes[0][1];
        
        for(int[] route: routes) {
            if(curLocation < route[0]) {
                curLocation = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}