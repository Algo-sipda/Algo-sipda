import java.util.*;

class Solution {
    Queue<int[]>[] course;
    int n, result;
    
    public int solution(int[][] points, int[][] routes) {
        n = routes.length;// 로봇 수 초기화
        course = new LinkedList[n];
        
        for(int i=0;i<n;i++) {
            course[i] = new LinkedList<>();
        }
        
        recode(points, routes);
        dangerousCnt();
        return result;
    }
    
    public void recode(int[][] points, int[][] routes) {
        for(int i=0;i<n;i++) {
            int[] route = routes[i];
            int r = points[route[0]-1][0];
            int c = points[route[0]-1][1];
            
            course[i].add(new int[]{r, c});
            
            for(int j=1;j<route.length;j++) {
                int pr = points[route[j]-1][0];
                int pc = points[route[j]-1][1];
                
                while(pr != r) {
                    if(pr > r) r++;
                    else r--;
                    
                    course[i].add(new int[]{r, c});
                }
                
                while(pc != c) {
                    if(pc > c) c++;
                    else c--;
                    
                    course[i].add(new int[]{r, c});
                }
            }
        }
    }
    
    public void dangerousCnt() {
        int cnt = 0;
        while(cnt < n) {
            int[][] map = new int[101][101];
            cnt = 0;
            
            for(int i=0;i<n;i++) {
                if(course[i].isEmpty()) {
                    cnt++;
                    continue;
                }
                
                int[] tmp = course[i].poll();
                map[tmp[0]][tmp[1]]++;
            }
            
            for(int i=0;i<map.length;i++) {
                for(int j=0;j<map[i].length;j++) {
                    if(map[i][j] > 1) result++;
                }
            }
        }
    }
}