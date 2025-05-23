// [PRO] 무인도 여행 https://school.programmers.co.kr/learn/courses/30/lessons/154540
import java.util.*;

class Solution {
    static int N, M;
    static char[][] box;
    static int landId = 1;
    static List<Integer> foods;
    static boolean[][] visited;
    static int[][] way = {{-1,0},{1,0},{0,1},{0,-1}};
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        box = new char[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                box[i][j] = maps[i].charAt(j);
            }
        }
        
        foods = new ArrayList<>();
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(isLand(box[i][j]) && !visited[i][j]) {
                    int sumOfFood = bfs(i, j, landId++);
                    foods.add(sumOfFood);
                }
            }
        }
        
        if(foods.size() == 0){
            return new int[]{-1};
        }
        Collections.sort(foods);
        int[] answer = foods.stream().mapToInt(Integer::intValue).toArray();;
        return answer;
    }
    static int bfs(int sx, int sy, int landId) {
        int sumOfFood = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            sumOfFood += box[now[0]][now[1]] - '0';
            for(int w = 0; w < 4; w++){
                int nx = now[0] + way[w][0];
                int ny = now[1] + way[w][1];
                if(inRange(nx, ny) && !visited[nx][ny] && isLand(box[nx][ny])) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx,ny});
                }
                
            }
        }
        return sumOfFood;
    }
    static boolean inRange(int x, int y){
        return x>=0 && x < N && y >=0 && y < M;
    }
    static boolean isLand(char x) {
        return x >= '1' && x <= '9';
    }
}