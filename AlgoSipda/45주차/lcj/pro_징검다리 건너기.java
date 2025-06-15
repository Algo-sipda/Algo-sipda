import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int left = 1;
        int right = 200_000_000;
        
        while(left <= right) {
            int mid = (left+right)/2;
            
            if(isPossible(mid, stones, k)) {
                answer = Math.max(answer, mid);
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return answer;
    }
    
    public boolean isPossible(int people, int[] stones, int k) {
        int num = 0;
        
        for(int stone:stones) {
            if(stone-people < 0) num++;
            else num = 0;
            
            if(k == num) return false;
        }
        
        return true;
    }
}