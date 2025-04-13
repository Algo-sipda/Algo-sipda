import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	private static int N, K, answer, lastIdx;
	private static int[] surface, depth;
	private static Node[] hole;
	private static final int MAX = 40001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;
		
		N = Integer.parseInt(br.readLine());
		
		answer = 0;
		surface = new int[MAX];
		depth = new int[MAX];
		
		br.readLine();
		for(int i=0;i<N/2-1;i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int j=x1;j<=x2;j++) {
				depth[j] = y1;
			}
			lastIdx = x2-1;
		}
		br.readLine();
		
		K = Integer.parseInt(br.readLine());
		
		hole = new Node[K];
		
		//수족관 배수구 데이터 입력
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dep = Integer.parseInt(st.nextToken());
			hole[i]= new Node(dep, idx);
		}
		
		for(Node current: hole) {
			int curDepth = current.r;
			int col = current.c;
			
			for(int i=col;i>=0;i--) {
				curDepth = curDepth>depth[i]?depth[i]:curDepth;
				surface[i] = curDepth>surface[i]?curDepth:surface[i];
			}
			
			curDepth = current.r;
			col = current.c;
			
			for(int i=col;i<=lastIdx;i++) {
				curDepth = curDepth>depth[i]?depth[i]:curDepth;
				surface[i] = curDepth>surface[i]?curDepth:surface[i];
			}
		}
		
		for(int i=0;i<=lastIdx;i++) {
			answer += depth[i] - surface[i];
		}
		System.out.println(answer);
	}

}