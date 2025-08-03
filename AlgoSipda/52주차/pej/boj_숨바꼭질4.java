import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	static int N, K; // 수빈이가 있는 위치, 동생이 있는 위치
	static int[] parent;
	static int[] time;
	public static void main(String[] args)throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		
		parent = new int[100001];
		time = new int[100001];
		
		bfs();
		
		int start = K;
		Stack<Integer> stack = new Stack<>();
		stack.add(start);
		
		while(start != N) { // 거꾸로 올라가야 함
			stack.add(parent[start]);					
			start = parent[start];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append((time[K] - 1)+"\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()+" ");
		}
		
		System.out.println(sb.toString());
		
	}
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		time[N] = 1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == K) {
				return;
			}
			if(isValid(now * 2)) {
				queue.add(now * 2);		
				parent[now * 2] = now;
				time [now * 2] = time[now] + 1;
			}
			if(isValid(now - 1)) {
				queue.add(now - 1);				
				parent[now - 1] = now;
				time [now - 1] = time[now] + 1;

			}
			if(isValid(now + 1)) {
				queue.add(now + 1);
				parent[now + 1] = now;
				time [now + 1] = time[now] + 1;

			}
			
		}
	}
	static boolean isValid(int x) {
		return x >= 0 && x <=100000 && time[x] == 0;
	}
}
 