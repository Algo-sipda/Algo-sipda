import java.util.*;

class Solution {
    
    public int solution(String name) {
        int answer = 0;
        int LRMaxMove = name.length();
        
        for(int i=0;i<name.length();i++) {
            int c = name.charAt(i)-'A';
            answer += Math.min(c, 26- c);
            
            int aIdx = i+1;
            while(aIdx < name.length() && name.charAt(aIdx) == 'A') {
                aIdx++;
            }
            
            // 좌우, 상하 최소 구하기
            LRMaxMove = Math.min(LRMaxMove, 2*i + name.length() - aIdx); // 순서대로
            LRMaxMove = Math.min(LRMaxMove, (name.length() - aIdx) *  2 + i); // 뒤로
        }
        
        
        return answer + LRMaxMove;
    }
}