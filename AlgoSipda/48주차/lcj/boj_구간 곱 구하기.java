import java.io.*;
import java.util.*;

public class Main_11505_구간곱구하기 {
	
	static int N, M, K;
	static int[] arr;
	static long[] tree;
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		tree = new long[N*4];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init(0, arr.length-1, 1);
		
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) update(0, arr.length-1, 1, b-1, c);
			else sb.append(segment(0, arr.length-1, 1, b-1, c-1)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static long init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		int mid = (start + end)/2;
		
		tree[node]  = (init(start, mid, node*2) * init(mid+1, end, node*2+1)) % MOD;
		return tree[node];
	}

	public static long update(int start, int end, int node, int idx, int c) {
		if(idx < start || idx > end)
			return tree[node];
		
		if(start == end)
			return tree[node] = c;
		
		int mid = (start + end)/2;
		
		tree[node] = (update(start, mid, node*2, idx, c) * update(mid+1, end, node*2+1, idx, c)) % MOD;
		
		return tree[node];
	}
	
	public static long segment(int start, int end, int node, int left, int right) {
		if(left > end || right < start)
			return 1;
		
		if(left <= start && right >= end)
			return tree[node];
		
		int mid = (start + end)/2;
		
		long t = (segment(start, mid, node*2, left, right) * segment(mid+1, end, node*2+1, left, right)) % MOD;
		
		return t;
	}
}