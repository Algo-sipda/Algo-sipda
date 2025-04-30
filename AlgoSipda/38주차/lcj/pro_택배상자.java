import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();
        
        for(int i=1;i<=order.length;i++) {
            q.add(i);
        }
        
        for(int o:order) {
            if(!s.isEmpty() && s.peek() == o) {
                s.pop();
                answer++;
            }
            else {
                boolean flag = false;
                
                while(!q.isEmpty()) {
                    if(q.peek() == o) {
                        answer++;
                        q.poll();
                        flag = true;
                        break;
                    }
                    else {
                        s.add(q.poll());
                    }
                }
                
                if(!flag) break;
            }
        }
        
        return answer;
    }
}