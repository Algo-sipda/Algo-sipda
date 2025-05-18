import java.util.*;
import java.awt.Point;

class Solution {
    
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int xLen, yLen;
    boolean[][] visited;
    
    public int[] solution(String[] maps) {
        yLen = maps.length;
        xLen = maps[0].length();
        visited = new boolean[yLen][xLen];
        List<Integer> list = new ArrayList<>();
        for(int y = 0; y < yLen; y++){
            for(int x = 0; x < xLen; x++){
                if(!visited[y][x] && maps[y].charAt(x) != 'X'){
                    int initFood = maps[y].charAt(x) - '0';
                    list.add(BFS(x, y, initFood, maps));
                }
            }
        }
        
        if(list.isEmpty()){
            return new int[]{-1};
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public int BFS(int x, int y, int initFood, String[] maps){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = true;
        int food = initFood;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Point p = queue.poll();
                for(int d = 0; d < 4; d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(0 <= nx && nx < xLen && 0 <= ny && ny < yLen){
                        if(!visited[ny][nx] && maps[ny].charAt(nx) != 'X'){
                            queue.add(new Point(nx, ny));
                            visited[ny][nx] = true;
                            food += maps[ny].charAt(nx) - '0';
                        }
                    }
                }
            }
        }
        return food;
    }
    
}