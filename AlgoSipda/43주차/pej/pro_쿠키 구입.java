// [PRO] 쿠키 구입
// https://school.programmers.co.kr/learn/courses/30/lessons/49995
// 가운데 기준점 기준으로 슬라이딩 윈도우 

class Solution {
    static int N;
    public int solution(int[] cookie) {
        int answer = 0;
        N = cookie.length;
        
        for(int m = 0; m <= N - 2; m++) { // m부터 왼쪽 누적합, m+1부터 오른쪽 누적합
            int left  = m;
            int right = m + 1;
            
            int lSum = cookie[left];
            int rSum = cookie[right];
            
            while(left >= 0 && right < N) {
                if(lSum == rSum) {
                    answer = Math.max(answer, lSum);
                    
                    left--;
                    right++;
                
                    if(left >= 0) lSum += cookie[left];
                    if(right < N) rSum += cookie[right];
                    
                } else if(lSum < rSum) {
                    left--;
                    if(left >= 0) lSum += cookie[left];
                } else {
                    right++;
                    if(right < N) rSum += cookie[right];
                }
                
            }
        }
        return answer;
    }
}