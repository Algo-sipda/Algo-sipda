//[PRO] 숫자 게임 https://school.programmers.co.kr/learn/courses/30/lessons/12987
// 질 때 : 가장 크게 지기 
// 이길 때 : 가장 작게 이기기 


import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int aLeft = 0, aRight = A.length - 1;
        // 질 때 : 가장 크게 지기 
        // 이길 때 : 가장 작게 이기기 
        for(int b = 0; b < B.length; b++){
            if(B[b] > A[aLeft]) {// B 선수 중 약한 사람이 A선수의 약한 선수보다 강할 경우 -> 이겨서 점수 + 1
                aLeft++;
                answer += 1;
            } else {
                aRight--; //A선수 중 강한 선수에게 져주기 
            }
            
        }
        return answer;
    }
}