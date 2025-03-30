class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int i = 0; i<=d;i+=k) {
            long dd = (long)d*d;
            long ii = (long)i*i;
            
            long top = (long)Math.sqrt(dd-ii);
            
            answer += (top / k)+1;
        }
        
        return answer;
    }
}