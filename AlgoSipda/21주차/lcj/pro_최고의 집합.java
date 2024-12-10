class Solution {
    public int[] solution(int n, int s) {   // n: 자연수 n개, s: 각 원소의 합
        int[] answer;
        
        if(n > s) {
            return new int[]{-1};
        }
        
        int quot = s/n;
        int remain = s%n;
        answer = new int[n];
        
        for(int i=n-1;i>=0;i--) {
            answer[i] = quot;// 몫으로 값 초기화
            if(remain > 0) {
                answer[i]++;
                remain--;
            }
        }
        
        return answer;
    }
}