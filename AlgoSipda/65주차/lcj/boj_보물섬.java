import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
    public static class Node {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static int n, m;
    static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		int max = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(board[i][j] == 'L') {
					visited = new boolean[n][m];
					int len = bfs(i, j);
					max = Math.max(max, len);
				}
			}
		}
		System.out.println(max);
	}
	
	private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;
        
        int len = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if(visited[nx][ny] == false && board[nx][ny] == 'L') {
                        q.offer(new Node(nx, ny, cur.cost + 1));
                        len = Math.max(len, cur.cost + 1);
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return len;
	}

}