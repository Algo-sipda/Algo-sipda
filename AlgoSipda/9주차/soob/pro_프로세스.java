import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int[] priorities, int location) {

        Queue<Point> queue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++){
            queue.add(new Point(priorities[i], i));
        }

        Arrays.sort(priorities);

        int cnt = 0;
        int answer = 0;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.x != priorities[priorities.length - cnt - 1]){
                queue.add(p);
            } else {
                cnt++;
                if(p.y == location){
                    answer = cnt;
                    break;
                }
            }
        }
        return answer;
    }
}