import java.util.*;

class Solution {
    
    public static final int INF = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        int[] people = new int[k];
        
        return calcu(0, n, k, people, reqs);
    }
    
    public static int calcu(int depth, int n, int k, int[] people, int[][] reqs) {
        if(depth == k && n == 0) {
            return calcWaitingTime(people, k, reqs);
        }
        
        if(depth == k) {
            return INF;
        }
        
        int result = INF;
        
        for(int i=1;i<=n;i++) {
            people[depth] = i;
            result = Math.min(result, calcu(depth+1, n-i, k, people, reqs));
        }
        return result;
    }
    
    public static int calcWaitingTime(int[] people, int k, int[][] reqs) {
        int waitingTime = 0;
        
        PriorityQueue<Integer>[] q = new PriorityQueue[k];
        
        for(int i=0;i<k;i++) {
            q[i] = new PriorityQueue<>();
        }
        
        for(int[] req: reqs) {
            int start = req[0];
            int during = req[1];
            int type = req[2]-1;
            int time = start;
            
            if(q[type].size() >= people[type]) {
                int end = q[type].poll();
                
                if(end > start) {
                    waitingTime += (end - start);
                    time = end;
                }
            }
            
            q[type].add(time + during);
        }
        return waitingTime;
    }
}