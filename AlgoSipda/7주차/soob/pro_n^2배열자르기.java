import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        
        int[] answer = new int[Long.valueOf(right-left+1).intValue()];
        
        for(long i = left; i <= right; i++){
            long quotient = i/n;
            long remainder = i%n;
            answer[Long.valueOf(i-left).intValue()] = Long.valueOf(Math.max(quotient, remainder) + 1).intValue();
        }
        
        return answer;
    }
}