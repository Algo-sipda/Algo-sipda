import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idx = 0;
        for(int i=0;i<B.length;i++) {
            if(A[idx] >= B[i]) continue;
            idx++;
        }
        
        return idx;
    }
}