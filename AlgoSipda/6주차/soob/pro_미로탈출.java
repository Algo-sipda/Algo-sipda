import java.util.*;
import java.awt.Point;

class Solution {
    
    int[] dx = {0,0,-1,1};
    int[] dy = {-1,1,0,0};
    
    public int solution(String[] maps) {
        int yLen = maps.length;
        int xLen = maps[0].length();
        char[][] map = new char[yLen][xLen];
        
        Point start = new Point();
        Point lever = new Point();
        Point exit = new Point();
        for(int y = 0; y < yLen; y++){
            for(int x = 0; x < xLen; x++){
                map[y][x] = maps[y].charAt(x);
                if(map[y][x] == 'S')
                    start = new Point(x, y);
                if(map[y][x] == 'L')
                    lever = new Point(x, y);
                if(map[y][x] == 'E')
                    exit = new Point(x, y);
            }
        }
                
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[yLen][xLen];
        int cnt = 0;
        int answer = 0;
        boolean leverCheck = false;
        boolean exitCheck = false;
        
        while(!queue.isEmpty()){
            int s = queue.size();
            for(int i = 0; i < s; i++){
                Point p = queue.poll();
                if(p.x == lever.x && p.y == lever.y){
                    answer += cnt;
                    queue.clear();
                    leverCheck = true;
                    break;
                }
                for(int d = 0; d < 4; d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(nx < 0 || ny < 0 || nx >= xLen || ny >= yLen)
                        continue;
                    if(visited[ny][nx])
                        continue;
                    if(map[ny][nx] != 'X'){
                        queue.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
            cnt++;
        }
        
        cnt = 0;
        queue.add(lever);
        visited = new boolean[yLen][xLen];
        while(!queue.isEmpty()){
            int s = queue.size();
            for(int i = 0; i < s; i++){
                Point p = queue.poll();
                if(p.x == exit.x && p.y == exit.y){
                    answer += cnt;
                    queue.clear();
                    exitCheck = true;
                    break;
                }
                for(int d = 0; d < 4; d++){
                    int nx = p.x + dx[d];
                    int ny = p.y + dy[d];
                    if(nx < 0 || ny < 0 || nx >= xLen || ny >= yLen)
                        continue;
                    if(visited[ny][nx])
                        continue;
                    if(map[ny][nx] != 'X'){
                        queue.add(new Point(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
            cnt++;
        }
        
        if(leverCheck && exitCheck)
            return answer;
        else
            return -1;
    }
}