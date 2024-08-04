// 문제 풀이: 이분 탐색
// 최저 운반 시간: 1, 최대 운반 시간: 1000000000(10^9) * 2 * 100000(10^5) * 2
// 도시의 개수: g.length => length

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        
        long left = 0;
        long right = (long)(Math.pow(10, 9) * 2 * Math.pow(10, 5) * 2);
        int len = g.length;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            int gold = 0, silver = 0, sum = 0;
            
            // 도시 순회
            for(int i=0;i<len;i++) {
                int curWeight = w[i];
                int curTime = t[i];
                
                // 몇번 왕복이 가능할지
                long cnt = mid / (curTime*2);
                // 편도로 추가 운반이 가능한 경우
                if((mid % (curTime*2)) >= curTime) {
                    cnt++;
                }
                
                gold += Math.min(g[i], curWeight*cnt);
                silver += Math.min(s[i], curWeight*cnt);
                sum += Math.min(g[i]+s[i], curWeight*cnt);
            }
            
            // 운반 가능하다면 시간 줄이기
            if(gold >= a && silver >= b && sum >= a + b) {
                answer = mid;
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }
        
        return answer;
    }
}