import java.io.*;
import java.util.*;

public class Main_20955_민서의응급수술 {

	static int N, M;
	static int[] parent;
	static int cnt, div;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=1;i<parent.length;i++) {
			parent[i] = i;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if(find(u) == find(v)) div++;
			else union(u, v);
		}
		
		count();
		System.out.println(div+cnt-1);
	}
	
	private static int find(int u) {
		if(u == parent[u])
			return u;
		
		return parent[u] = find(parent[u]);
	}
	
	private static void union(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);
		
		if(n1 != n2) {
			parent[n2] = n1;
		}
	}
	
	private static void count() {
		for(int i=1;i<N+1;i++) {
			if(parent[i] == i) {
				cnt++;
			}
		}
	}

}
