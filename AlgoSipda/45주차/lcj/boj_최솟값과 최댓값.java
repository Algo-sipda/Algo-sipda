import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static long[] arr, minSegment, maxSegment;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new long[N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		int h = (int)Math.ceil(Math.log(N)/Math.log(2));
		int size = (int)Math.pow(2, h+1);
		
		minSegment = new long[size];
		maxSegment = new long[size];
		
		minInit(1, N, 1);
		maxInit(1, N, 1);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			long n1 = Long.parseLong(st.nextToken());
			long n2 = Long.parseLong(st.nextToken());
			
			sb.append(minSum(1, N, 1, n1, n2)+" "+maxSum(1, N, 1, n1, n2)+"\n");
		}
		System.out.print(sb);
	}

	private static long minInit(int start, int end, int node) {
		if(start == end) {
			return minSegment[node] = arr[start];
		}
		
		int mid = (start + end)/2;
		return minSegment[node] = Math.min(minInit(start, mid, node*2), 
											minInit(mid+1, end, node*2+1));
	}
	
	private static long maxInit(int start, int end, int node) {
		if(start == end) {
			return maxSegment[node] = arr[start];
		}
		
		int mid = (start+end)/2;
		return maxSegment[node] = Math.max(maxInit(start, mid, node*2), 
											maxInit(mid+1, end, node*2+1));
	}
	
	private static long minSum(int start, int end, int node, long left, long right) {
		if(right<start || end <left) return Integer.MAX_VALUE;
		
		if(left<=start && end <= right)
			return minSegment[node];
		
		int mid = (start+end)/2;
		return Math.min(minSum(start, mid, node*2, left, right), 
						minSum(mid+1, end, node*2+1, left, right));
	}
	
	private static long maxSum(int start, int end, int node, long left, long right) {
		if(right<start || end <left) return 0;
		
		if(left<=start && end<=right)
			return maxSegment[node];
		
		int mid = (start+end)/2;
		return Math.max(maxSum(start, mid, node*2, left, right), 
						maxSum(mid+1, end, node*2+1, left, right));
	}
}