import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end;
	int weihgt;
	public Node(int end, int weihgt) {
		this.end = end;
		this.weihgt = weihgt;
	}
	@Override
	public int compareTo(Node o) {
		return this.weihgt - o.weihgt;
	}
}

public class Main {
	
	static int N, M, X;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Node>> arrList, reverse;
	static boolean[] visited;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arrList = new ArrayList<>();//문제의 입력을 그대로 받은 배열
		reverse = new ArrayList<>();//반대로 입력
		
		for(int i=0;i<N+1;i++) {//0은 사용안할 것이기 때문에
			arrList.add(new ArrayList<>());
			reverse.add(new ArrayList<>());
		}
		
		//단방향 인접리스트로 구현
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			arrList.get(start).add(new Node(end, weight));
			reverse.get(end).add(new Node(start, weight));
		}
		
		int[] distance1 = dijkstra(arrList);//X에서 시작점들 사이의 최단 거리
		int[] distance2 = dijkstra(reverse);//시작점들에서 X사이의 최단거리
		
		int answer = 0;
		for(int i=1;i<N+1;i++) {
			answer = Math.max(answer, distance1[i]+distance2[i]);//가장 오래 걸리는 학생의 소요 시간
		}
		System.out.println(answer);
	}

	//다익스트라
	private static int[] dijkstra(ArrayList<ArrayList<Node>> list) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0));
		
		visited = new boolean[N+1];//1번 사용 X
		distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[X] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int curEnd = cur.end;
			
			if(visited[curEnd])continue;
			
			visited[curEnd] = true;
			
			for(Node n:list.get(curEnd)) {
				if(!visited[n.end] && distance[n.end] > (distance[curEnd] + n.weihgt)) {
					distance[n.end] = distance[curEnd] + n.weihgt;
					pq.add(new Node(n.end, distance[n.end]));
				}
			}
		}
		
		return distance;
	}
}
