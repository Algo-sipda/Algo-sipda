import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int n = queue1.length;
        
        long[] merged = new long[n * 2];
        
        for (int i = 0; i < n; i++) {
            merged[i] = queue1[i];
        }

        for (int i = 0; i < n; i++) {
            merged[n + i] = queue2[i];
        }
       
        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();
        
        if ((q1Sum+ q2Sum) % 2 != 0) {
            return -1;
        }
        
        long target = (q1Sum+ q2Sum) / 2;
        
        int l1 = 0;
        int r1 = n - 1;
                
        int l2 = n;
        int r2 = n * 2 - 1;
        
        while (answer < 4 * n) {
            if (q1Sum < target) {
                r1 = (r1 + 1) % merged.length;          
                l2 = (l2 + 1) % merged.length; 

				q1Sum += merged[r1];
				q2Sum -= merged[r1];

				answer += 1;
            }
            
            if (q2Sum < target) {
                r2 = (r2 + 1) % merged.length; 
                l1 = (l1 + 1) % merged.length; 
                
                q2Sum += merged[r2];
                q1Sum -= merged[r2];
                
                answer += 1;
            }

            if (q1Sum == target && q2Sum == target) {
                break;
            }
        }

        return q1Sum == q2Sum ? answer : -1;
    }
}