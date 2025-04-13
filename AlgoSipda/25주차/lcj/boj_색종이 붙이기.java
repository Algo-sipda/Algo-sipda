import java.io.*;
import java.util.*;

public class Main_17136_색종이붙이기 {
	
	static boolean[][] visited;
	static int[][] board;
	static int[] paper;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		visited = new boolean[10][10];
		board = new int[10][10];
		paper = new int[5];
		
		for(int i=0;i<10;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE?-1:answer);
	}

	static void dfs(int depth, int cnt) {
		if(depth == 100) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		int r = depth / 10;
		int c = depth % 10;
		
		if(board[r][c] == 0 || visited[r][c]) {
			dfs(depth+1, cnt);
		}
		else {
			for(int i=1;i<=5;i++) {
				if(check(r, c, i, true)) {
					dfs(depth+1, cnt+1);
					check(r, c, i, false);
				}
			}
		}
	}
	
	static boolean check(int r, int c, int num, boolean flag) {
		if(flag) {
			if(paper[num-1] == 5) return false;
			
			// 붙일 수 있는지 확인
			for(int i=r; i<r+num;i++) {
				for(int j=c; j<c+num;j++) {
					if(i>=10 || j>= 10 || visited[i][j] || board[i][j] == 0) return false;
				}
			}
			
			paper[num-1]++;
		}
		else {
			paper[num-1]--;
		}
		
		for(int i=r; i<r+num;i++) {
			for(int j=c ;j<c+num;j++) {
				visited[i][j] = flag;
			}
		}
		return true;
	}
}