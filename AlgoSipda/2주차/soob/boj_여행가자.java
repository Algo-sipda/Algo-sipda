import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int parent[];
	
	static int find (int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union (int x, int y) {
		x = find(x);
		y = find(y);
		if (x!=y) {
			if (x>y) parent[x] = y;
			else parent [y] = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(bf.readLine());
		
		parent = new int [N+1];
		for (int i=1; i<N+1; i++) parent[i] =i;
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j=1; j<N+1; j++) {
				int sel = Integer.parseInt(st.nextToken());
				if (sel == 1) union(i, j);
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		int start = Integer.parseInt(st.nextToken());
		boolean flag = true;
		for (int i=1; i<M; i++) {
			int p = Integer.parseInt(st.nextToken()); 
			if (find(start) == find(p)) continue;
			flag = false;
		}
		
		if (flag)
            System.out.println("YES");
		else
            System.out.println("NO");
	}

}