class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0) {
            int mod = storey % 10;
            storey = storey / 10;
            
            if(mod == 5) {
                if(storey%10 >= 5) {
                    answer += (10-mod);
                    storey++;
                }
                else {
                    answer += mod;
                }
            }
            else if(mod > 5) { // 더하는 것이 좋음
                answer += (10-mod);
                storey++;
            }
            else { // 빼는 것이 좋음
                answer += mod;
            }
        }
        
        return answer;
    }
}