class Solution {
    public int solution(int n) {
        int answer = n+1;
        
        int curCnt = toBinary(n);
        while(true) {
            int nextCnt = toBinary(answer);
            if(curCnt == nextCnt) break;
            answer++;
        }
        
        return answer;
    }
    
    public int toBinary(int n) {
        int cnt = 1;
        
        while(n > 1) {
            if(n % 2 == 1) cnt++;
            n /= 2;
        }
        
        return cnt;
    }
}