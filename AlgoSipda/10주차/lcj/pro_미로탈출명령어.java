class Solution {
    
    int N, M;
    int sx, sy, ex, ey, len;
    String answer = null;
    
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    String[] dir = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m;
        sx = x; sy = y; ex = r; ey = c; len = k;
        
        int maxDist = Math.abs(ex-sx) + Math.abs(ey-sy);
        
        // 거리로 우선 탈출 가능한지 아닌지 판별
        if(maxDist%2 != k%2 || maxDist > k) return "impossible";
        
        dfs(sx, sy, new StringBuilder(""));
        
        return answer;
    }
    
    public void dfs (int x, int y, StringBuilder sb) {
        if(answer != null) return;
        
        if( sb.length() == len ) {
            if(x == ex && y == ey) {
                answer = sb.toString();
            }
            return;
        }
        
        // 여기 부분 없으면 시간 초과
        int dist = Math.abs(ex - x) + Math.abs(ey-y);
        if(sb.length() + dist > len) return;
        
        for(int d = 0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
            
            sb.append(dir[d]);
            
            dfs(nx, ny, sb);
            sb.delete(sb.length()-1, sb.length());
        }
    }
}