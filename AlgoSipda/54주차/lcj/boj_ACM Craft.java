import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, W;
	static int[] d;
	static ArrayList<Integer>[] buildings;
	static int[] degree;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<=T;test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			d = new int[N+1];
			degree = new int[N+1];
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1;i<=N;i++) {
				d[i] = Integer.parseInt(st.nextToken());
			}
			
			buildings = new ArrayList[N+1];
			for(int i=0;i<=N;i++) {
				buildings[i] = new ArrayList<>();
			}
			
			for(int i=0;i<K;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				buildings[x].add(y);
				degree[y]++;//y건물이 지어지기 위해서는 x가 지어져야 함 -> y에 대한 degree 증가
			}
			W = Integer.parseInt(br.readLine());
			
			answer = 0;
			//앞에서 부터 탐색 시작
			checkList();
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}

	private static void checkList() {
		Queue<Integer> q = new ArrayDeque<>();
		int[] result = new int[N+1];
		for(int i=1;i<=N;i++) {
			result[i] = d[i];
			if(degree[i] == 0) {
				//제약이 없는 경우 큐에 삽입
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();//하위 건물
			for(Integer n : buildings[cur]) {
				result[n] = Math.max(result[n], result[cur]+d[n]);
				degree[n]--;
				
				if(degree[n]==0)//요구하는 건물이 없는 건물일 경우
					q.add(n);
			}
		}
		answer = result[W];
	}
}