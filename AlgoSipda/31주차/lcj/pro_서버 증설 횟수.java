import java.util.*;

class Server {
    int expiredTime, cnt;

    public Server(int expiredTime, int cnt) {
        this.expiredTime = expiredTime;
        this.cnt = cnt;
    }
}

class Solution {
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Server> pq = new LinkedList<>();
        
        int curCnt = 0;
        int totalCnt = 0;
        
        for(int i=0;i<players.length;i++) {
            while(!pq.isEmpty() && pq.peek().expiredTime == i) {
                curCnt -= pq.poll().cnt;
            }
            
            int needCnt = players[i] / m;
            // 더 증설할 서버가 있는지 체크
            int addCnt = needCnt - curCnt;
            
            if(addCnt > 0) {
                curCnt += addCnt;
                totalCnt += addCnt;
                pq.add(new Server(i + k, addCnt));
            }
        }
        
        return totalCnt;
    }
}