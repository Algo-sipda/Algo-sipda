import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Node{
		int end, cost;
		
		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}
	
	private static int N, M;
	private static int factory1, factory2;
	private static ArrayList<Node> arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			arr[i] = new ArrayList<Node>();
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			max = Math.max(max, weight);
			min = Math.min(min, weight);
			arr[start].add(new Node(end, weight));
			arr[end].add(new Node(start, weight));
		}
		
		st = new StringTokenizer(br.readLine()," ");
		factory1 = Integer.parseInt(st.nextToken());
		factory2 = Integer.parseInt(st.nextToken());
		
		int result = 0;
		while(min<=max) {
			int mid = (min+max)/2;
			
			// factory1 ~ factory2까지 mid의 중량이 건널 수 있는지 확인
			if(bfs(mid)) {
				// 가능
				min = mid+1;
				result = mid;
			}else {
				max = mid-1;
			}
		}
		System.out.println(result);
	}
	
	private static boolean bfs(int mid) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.add(factory1);
		visited[factory1] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == factory2) return true;
			
			for(int i=0;i<arr[cur].size();i++) {
				if(arr[cur].get(i).cost>= mid && !visited[arr[cur].get(i).end]) {
					visited[arr[cur].get(i).end] = true;
					q.offer(arr[cur].get(i).end);
				}
			}
		}
		return false;
	}

}