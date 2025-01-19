import java.util.*;

/*
    풀이 방법: 이진탐색
*/

class Solution {    
    public int solution(int[] diffs, int[] times, long limit) {
        return binarySearch(diffs, times, limit);
    }
    
    public int binarySearch(int[] diffs, int[] times, long limit) {
        int min = 100000;
        int max = 1;
        
        while(max <= min) {
            int midLevel = (min+max) / 2;
            
            long mid = 0;
            
            for(int i=0;i<diffs.length;i++) {
                if(diffs[i] <= midLevel) {
                    mid += times[i];
                }
                else {
                    mid += (long)(times[i] + times[i-1]) * (long)(diffs[i] - midLevel) + times[i];
                }
            }
            
            // 제한 시간을 초과한 경우
            if(mid > limit) {
                max = midLevel + 1;
            }
            else {
                min = midLevel - 1;
            }
        }
        
        return max;
    }
}