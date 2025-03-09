class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while(s.length() > 1) {
            int one = 0;
            
            for(int i=0;i<s.length();i++) {
                if(s.charAt(i) == '0') answer[1]++;
                else one++;
            }
            
            // one을 2진수로 변환 후 s에 저장
            s = Integer.toBinaryString(one);
            answer[0]++;
        }
        
        return answer;
    }
}