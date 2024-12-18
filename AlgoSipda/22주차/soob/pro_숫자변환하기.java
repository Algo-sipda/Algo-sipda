import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        Set<Integer> set = new HashSet<>();
        int answer = -1;
        int count = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int s = 0; s < size; s++){
                int p = queue.poll();
                if(p == y){
                    answer = count;
                    break;
                }
                for(int i = 2; i <= 3; i++){
                    if(!set.contains(i*p) && i*p <= y){
                        queue.add(i*p);
                        set.add(i*p);
                    }
                    if(!set.contains(p+n) && p+n <= y){
                        queue.add(p+n);
                        set.add(p+n);
                    }
                }

            }
            count++;
        }
        return answer;
    }
}