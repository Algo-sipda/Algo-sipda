import java.util.*;

class Solution {
    class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    
    int N, M;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        int[][] visit = new int[N][M];
        Queue<Point> q = new ArrayDeque();
        
        for(int i = 0 ; i < N; ++i){
            String s = board[i];
            for(int j = 0 ; j < M; ++j){
                if(s.charAt(j) == 'R'){
                    q.add(new Point(i,j));
                    visit[i][j] = 1;
                    break;
                }
            }
        }
        
        int answer = -1;
        while(!q.isEmpty()){
            Point cur = q.poll();
            
            if(board[cur.r].charAt(cur.c) == 'G'){
                answer = visit[cur.r][cur.c] - 1;
                break;
            }
            
            for(int d = 0 ; d < 4; ++d){
                int nextR = cur.r + dr[d];
                int nextC = cur.c + dc[d];
                while(true){
                    if(isRange(nextR, nextC) && board[nextR].charAt(nextC) != 'D'){
                        nextR += dr[d];
                        nextC += dc[d];
                    }else{
                        nextR -= dr[d];
                        nextC -= dc[d];
                        break;
                    }
                }
                
                // 해당 지점에 방문한적이 없다면, 해당 지점에서 탐색한다.
                if(visit[nextR][nextC] == 0){
                    q.add(new Point(nextR, nextC));
                    visit[nextR][nextC] = visit[cur.r][cur.c] + 1;
                }
            }
        }
        
        return answer;
    }
    
    
    public boolean isRange(int r, int c){
        return (0 <= r && r < N && 0 <= c && c < M);
    }
}