import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[101];
        List<Integer> box = new LinkedList<>();
        
        for(int i=0;i<cards.length;i++) {
            Queue<Integer> q = new LinkedList<>();
            if(!visited[cards[i]-1]) {
                q.add(cards[i]-1);
                visited[cards[i]-1] = true;
                int cnt = 0;
                
                while(!q.isEmpty()) {
                    int cur = q.poll();
                    int next = cards[cur]-1;
                    cnt++;
                    
                    if(visited[next]) {
                        // 방문한 경우
                        break;
                    }
                    q.add(next);
                    visited[next] = true;
                }
                
                box.add(cnt);
            }
        }
        
        
        Collections.sort(box, Collections.reverseOrder());
        int answer = box.size() <= 1? 0: box.get(0)*box.get(1);
        
        return answer;
    }
}