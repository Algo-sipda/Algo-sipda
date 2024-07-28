import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey != 0){
            int x = storey % 10;
            boolean plus = true;
            if(x < 10-x){       // 마지막 자리수가 4 이하이면
                plus = false;              
            }else if (x == 5){  // 5이면 앞의 자리 수 확인
                if((storey % 100) < 50) // 앞의 자리 수가 50 미만이면
                    plus = false;               
            }
            
            if(!plus){              // 내려가기
                answer += x;
                storey /= 10;
            }else{                  // 올라가기
                answer += 10 - x;
                storey /= 10; 
                storey ++;
            }
        }
        return answer;
    }
}