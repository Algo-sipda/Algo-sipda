package AlgoSipda.7주차.soob;

public class pro_카카오프렌즈컬러링북 {
    
}
import java.util.*;
import java.awt.Point;

class Solution {
    
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        int maxSizeOfOneArea = 0;
        int numberOfArea = 0;
        
        int yLen = picture.length;
        int xLen = picture[0].length;
        boolean[][] visited = new boolean[yLen][xLen];
        
        for(int y = 0; y < yLen; y++){
            for(int x = 0; x < xLen; x++){
                if(!visited[y][x] && picture[y][x] != 0){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea,BFS(visited, picture, x, y));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int BFS(boolean[][] visited, int[][] picture, int x, int y){
        int xLen = visited[0].length;
        int yLen = visited.length;
        int color = picture[y][x];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[y][x] = true;
        int count = 1;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Point p = queue.poll();
                for(int d = 0; d < 4; d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(nx < 0 || ny < 0 || nx >= xLen || ny >= yLen)
                        continue;
                    if(!visited[ny][nx] && picture[ny][nx] == color){
                        queue.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}