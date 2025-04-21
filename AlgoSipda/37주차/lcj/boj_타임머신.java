import java.io.*;
import java.util.*;

/**
 * 문제 유형 : 벨만포드(음수 가중치 허용)
 */

public class Main_11657_타임머신 {
	
	static class Node {
		int end, cost;
		
		public Node(int end,int cost) {
			this.end = end;
			this.cost = cost;
		}
	}
	
	static int N, M;
	static ArrayList<ArrayList<Node>> list;
	static long[] dist;
	
	static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		dist = new long[N+1];
		
		for(int i=0;i<=N;i++) {
			list.add(new ArrayList<>());
			dist[i] = INF;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(start).add(new Node(end, cost));
		}
		
		if(bellmanford()) {
			sb.append("-1");
		}
		else {
			for(int i=2;i<=N;i++) {
				if(dist[i] == INF) sb.append("-1\n");
				else sb.append(dist[i]+"\n");
			}
		}
		System.out.print(sb);
	}
	
	static boolean bellmanford() {
		dist[1] = 0;
		boolean check = false;
		
		for(int i=1;i<N;i++) {
			check = false;
			for(int j=1;j<=N;j++) {
				for(Node n : list.get(j)) {
					if(dist[j] == INF) break;
					
					if(dist[n.end] > dist[j] + n.cost) {
						dist[n.end] = dist[j] + n.cost;
						check = true;
					}
				}
			}
			
			if(!check) break; 
		}
		
		// 음수 사이클 존재 체크
		if(check) {
			for(int i=1;i<=N;i++) {
				for(Node n: list.get(i)) {
					if(dist[i] == INF) break;
					
					if(dist[n.end] > dist[i] + n.cost) return true;
				}
			}
		}
		
		return false;
	}

}