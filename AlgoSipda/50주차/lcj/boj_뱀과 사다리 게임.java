import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] ladderAndSnake;
	static int[] count;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ladderAndSnake = new int[101];
		count = new int[101];
		visited = new boolean[101];
		
		for(int i=0;i<N+M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladderAndSnake[a] = b;
		}
		
		bfs();
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		count[1] = 0;
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == 100) {
				System.out.println(count[cur]);
				return;
			}
			
			for(int i=1;i<=6;i++) {
				int nextCur = cur + i;
				if(nextCur>100) continue;
				if(visited[nextCur]) continue;
				
				visited[nextCur] = true;
				
				if(ladderAndSnake[nextCur] != 0) {
					if(!visited[ladderAndSnake[nextCur]]) {
						q.add(ladderAndSnake[nextCur]);
						visited[ladderAndSnake[nextCur]] = true;
						count[ladderAndSnake[nextCur]] = count[cur] + 1;
					}
				}
				else {
					q.add(nextCur);
					count[nextCur] = count[cur] + 1;
				}
			}
		}
	}
}