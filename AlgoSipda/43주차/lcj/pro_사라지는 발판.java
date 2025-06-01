class Solution {
    
    int[][] map;
    int N, M;
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        map = board;
        N = board.length;
        M = board[0].length;
        
        int answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return answer;
    }
    
    public int dfs(int p1r, int p1c, int p2r, int p2c) {
        if(map[p1r][p1c] == 0) return 0;
        
        int answer = 0;
        for(int d = 0; d<4;d++) {
            int nr = p1r + dr[d];
            int nc = p1c + dc[d];
            
            if(!isRange(nr, nc) || map[nr][nc] == 0) {
                continue;
            }
            
            map[p1r][p1c] = 0;
            int val = dfs(p2r, p2c, nr, nc) + 1;
            map[p1r][p1c] = 1;
            
            if(answer % 2 == 0 && val % 2 == 1) answer = val;
            else if(answer % 2 == 0 && val % 2 == 0) answer = answer > val? answer : val;
            else if(answer % 2 == 1 && val % 2 == 1) answer = answer < val? answer : val;
        }
        return answer;
    }
    
    public boolean isRange(int r, int c) {
        return 0<= r && r<N && 0<=c && c<M;
    }
}