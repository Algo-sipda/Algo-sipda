import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int last = 0; 
        for(int[] target : targets){
            if(last <= target[0]){
                answer++;
                last = target[1];
            }
        }

        return answer;
    }
}