import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree{
	long tree[];
	int treeSize;
	
	public SegmentTree(int arrsize) {
		int h = (int)Math.ceil(Math.log(arrsize)/Math.log(2));
		this.treeSize = (int)Math.pow(2, h+1);
		tree = new long[treeSize];
	}
	
	public long init(long[] arr, int node, int start, int end) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		
		return tree[node] = init(arr, node*2, start, (start+end)/2)+init(arr, node*2+1, (start+end)/2+1, end);
	}
	
	public void update(int node, int start, int end, int idx, long diff) {
		if(idx<start || end < idx) return;
		
		tree[node] += diff;
		
		if(start != end) {
			update(node*2, start, (start+end)/2, idx, diff);
			update(node*2+1, (start+end)/2+1, end, idx, diff);
		}
	}
	
	public long sum(int node, int start, int end, int left, int right) {
		if(left>end || right <start)
			return 0;
		
		if(left<=start && end <= right)
			return tree[node];
		
		return sum(node*2, start, (start+end)/2, left, right) + sum(node*2+1, (start+end)/2+1, end, left, right); 
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N+1];
		
		for(int i=1;i<=N;i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		SegmentTree stree = new SegmentTree(N);
		stree.init(arr, 1, 1, N);
		
		for(int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			//1 - 데이터 변경 명령어
			if(cmd == 1) {
				stree.update(1, 1, N, a, b-arr[a]);
				arr[a] = b;
			}
			//그 외 - 구간합 명령어
			else {
				sb.append(stree.sum(1, 1, N, a, (int)b)).append("\n");
			}
		}
		System.out.print(sb);
	}

}
