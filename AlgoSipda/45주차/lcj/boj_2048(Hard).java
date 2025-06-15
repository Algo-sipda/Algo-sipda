import java.io.*;
import java.util.*;

/**
 * 풀이 정답 참조..
 */

public class Main {

	static int n, answer;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());

		graph = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(answer);
	}
	
	public static void dfs(int cnt) {
		if(cnt == 10) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					answer = Math.max(answer, graph[i][j]);
				}
			}
			return;
		}
		
		int[][] tmp = new int[n][n];
		tmp = copyMap(tmp, graph);
		
		left();
		dfs(cnt+1);
		graph = copyMap(graph, tmp);

		right();
		dfs(cnt+1);
		graph = copyMap(graph, tmp);

		top();
		dfs(cnt+1);
		graph = copyMap(graph, tmp);

		down();
		dfs(cnt+1);
		graph = copyMap(graph, tmp);
	}
	
	public static void left() {
		for(int i=0;i<n;i++) {
			int curP = 0;
			int tmpCnt = 0;
			
			for(int j=0;j<n;j++) {
				if(graph[i][j] == 0) continue;
				
				if(graph[i][j] == tmpCnt) {
					graph[i][curP-1] = tmpCnt*2;
					tmpCnt = 0;
					graph[i][j] = 0;
				}
				else {
					tmpCnt = graph[i][j];
					graph[i][j] = 0;
					graph[i][curP++] = tmpCnt;
				}
			}
		}
	}
	
	public static void right() {
		for(int i=0;i<n;i++) {
			int curP = n-1;
			int tmpCnt = 0;
			
			for(int j=n-1;j>=0;j--) {
				if(graph[i][j] == 0) continue;
				
				if(graph[i][j] == tmpCnt) {
					graph[i][curP+1] = tmpCnt*2;
					tmpCnt = 0;
					graph[i][j] = 0;
				}
				else {
					tmpCnt = graph[i][j];
					graph[i][j] = 0;
					graph[i][curP--] = tmpCnt;
				}
			}
		}
	}
	
	public static void top() {
		for(int i=0;i<n;i++) {
			int curP = 0;
			int tmpCnt = 0;
			
			for(int j=0;j<n;j++) {
				if(graph[j][i] == 0) continue;
				
				if(graph[j][i] == tmpCnt) {
					graph[curP-1][i] = tmpCnt*2;
					tmpCnt = 0;
					graph[j][i] = 0;
				}
				else {
					tmpCnt = graph[j][i];
					graph[j][i] = 0;
					graph[curP++][i] = tmpCnt;
				}
			}
		}
	}
	
	public static void down() {
		for(int i=0;i<n;i++) {
			int curP = n-1;
			int tmpCnt = 0;
			
			for(int j=n-1;j>=0;j--) {
				if(graph[j][i] == 0) continue;
				
				if(graph[j][i] == tmpCnt) {
					graph[curP+1][i] = tmpCnt*2;
					tmpCnt = 0;
					graph[j][i] = 0;
				}
				else {
					tmpCnt = graph[j][i];
					graph[j][i] = 0;
					graph[curP--][i] = tmpCnt;
				}
			}
		}
	}
	
	public static int[][] copyMap(int[][] tmp, int[][] graph) {
		for(int i=0;i<tmp.length;i++) {
			for(int j=0;j<tmp[i].length;j++) {
				tmp[i][j] = graph[i][j];
			}
		}
		return tmp;
	}
}