// [PRO] 택배상자 https://school.programmers.co.kr/learn/courses/30/lessons/131704

import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        // 1 - n 
        int i = 1; // boxId
        Stack<Integer> stack = new Stack<>();
        for(int o : order){
           // System.out.println(o);
            while(i <= o) {
                stack.add(i);
                i++;
            }
            //System.out.println(stack);
            if(!stack.isEmpty() && o == stack.peek()) {
                stack.pop();
                answer += 1;
            }else {
                break;
            }
        }

        // 보조 Stack<Integer> 
        return answer;
    }
}