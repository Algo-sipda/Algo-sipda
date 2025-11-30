import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static List<Integer>[] list;
	static int[] wv;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n;i++) {
			list[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num != -1) {
				list[num].add(i);
			}
		}
		
		wv = new int[n+1];
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int praiseNum = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			wv[praiseNum] += w;
		}
		
		dfs(1);
		for(int i=1;i<=n; i++) {
			sb.append(wv[i]+" ");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int idx) {
		for(int num: list[idx]) {
			wv[num] += wv[idx];
			dfs(num);
		}
	}

}