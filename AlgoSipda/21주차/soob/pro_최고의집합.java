import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s){
            int[] answer = {-1};
            return answer;
        } else {
            int[] answer = new int[n];
            int total = s;
            int x = s/n;
            
            for(int i = 0; i < n; i++){
                answer[i] = x;
                total -= x;
            }
        
            for(int i = 0; i < total; i++){
                answer[n - i - 1]++;
            }
            
            return answer;
        }
    }
}