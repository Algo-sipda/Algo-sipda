import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int total = 0;
	static boolean[] truePerson = new boolean[51];
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//초기화
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i] = i;
		}
		
		//진실 아는 사람 정보만 받아오기
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			truePerson[Integer.parseInt(st.nextToken())] = true;
		}
		
		//같은 파티에 있는 사람들 union
		ArrayList<Integer>[] peoples = new ArrayList[M];
		for(int i=0; i<M; i++) {
			peoples[i] = new ArrayList<>();
		}

		int value, pre =0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if(n > 0) {
				pre = Integer.parseInt(st.nextToken());
				peoples[i].add(pre);
			}
			for(int j=1; j<n; j++) {
				value = Integer.parseInt(st.nextToken());
				peoples[i].add(value);
				union(pre, value);
				pre = value;
			}
		}
		
		int parent;
		for(int i=1; i<truePerson.length; i++) {
			if(truePerson[i]) {
				truePerson[find(i)] = true;
			}
		}
		
		for(int i=0; i<M; i++) {
			if(peoples[i].size() > 0) {
				parent = find(peoples[i].get(0));
				// 진실을 아는 사람들과 파티를 같이 하지 않았으면 total++
				if(!truePerson[parent]) total++;
			}
		}
		
		System.out.println(total);
	}
	

	private static int find(int x) {
		if(parent[x] == x) 
			return parent[x] = x;
		else  return find(parent[x]);
		
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!= b) {
			if(a>b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
			return true;
		}
		return false;
	}

}