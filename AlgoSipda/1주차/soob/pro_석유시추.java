import java.util.*;
import java.awt.Point;
import java.lang.Math;

class Solution {
    
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};
    boolean[][] visited;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        int height = land.length;
        int width = land[0].length;
        
        visited = new boolean[height][width];
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(land[y][x] == 1 && !visited[y][x]){
                    Map<Integer, Set<Integer>> BFSmap = BFS(x, y, land);
                    for(Map.Entry<Integer, Set<Integer>> entry: BFSmap.entrySet()){
                        for(int i : entry.getValue()){
                            if(map.containsKey(i))
                                map.put(i, map.get(i) + entry.getKey());
                            else
                                map.put(i, entry.getKey());
                            answer = Math.max(answer, map.get(i));
                        }
                    }
                }
            }
        }
                
        return answer;
    }
    
    public Map BFS(int x, int y, int[][] land){
        
        Set<Integer> set = new HashSet<>();
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = true;
        
        int oil = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Point p = queue.poll();
                oil++;
                set.add(p.x);
                for(int d = 0; d < 4; d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(0 <= ny && ny < land.length && 0 <= nx && nx < land[0].length){
                        if(land[ny][nx] == 1 && !visited[ny][nx]){
                            visited[ny][nx] = true;
                            queue.add(new Point(nx, ny));
                        }
                    }
                }
            }
        }
        
        Map<Integer, Set> map = new HashMap<>();
        map.put(oil, set);
        
        return map;
    }
    
    
}