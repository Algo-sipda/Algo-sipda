import java.io.*;
import java.util.*;

/**
 * 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 함.
 * 단계별로 돌을 움직임
 * => 크기가 같지 않은 두 그룹 고름 -> X돌의 개수 X+X / Y돌의 개수 Y-X(X : 작은쪽, Y : 큰쪽)
 * 
 * 강호가 돌을 같은 개수로 만들 수 있으면 1
 * 아니면 0 출력
 * 
 * @author lcj52
 *
 */

public class Main {
	
	static class Stone {
		int s1, s2, s3;
		
		public Stone(int s1, int s2, int s3) {
			int[] arr = {s1, s2, s3};
			Arrays.sort(arr);
			this.s1 = arr[0];
			this.s2 = arr[1];
			this.s3 = arr[2];
		}
	}
	
	static Stone stones;
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		stones = new Stone(A, B, C);
		sum = A + B + C;
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Stone> q = new LinkedList<>();
		boolean[][] visited = new boolean[1501][1501];
	
		q.add(stones);
		visited[stones.s1][stones.s3] = true;
		
		while(!q.isEmpty()) {
			Stone cur = q.poll();
			
			if(cur.s1 == cur.s2 && cur.s2 == cur.s3) {
				return 1;
			}
			
			int[][] pairs = {{cur.s1, cur.s2}, {cur.s1, cur.s3}, {cur.s2, cur.s3}};
			
			for(int[] pair:pairs) {
				int s1 = pair[0] + pair[0];
				int s3 = pair[1] - pair[0];
				
				if(s3<0) continue;
				if(visited[s1][s3]) continue;
				
				q.add(new Stone(s1, s3, sum-s1-s3));
				visited[s1][s3] = true;
			}
		}
		
		return 0;
	}
}