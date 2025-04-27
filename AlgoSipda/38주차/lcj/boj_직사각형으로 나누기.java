import java.io.*;
import java.util.*;

public class Main_1451_직사각형으로나누기 {
	
	static int N, M;
	static int[][] arr;
	static long[][] sum;
	static long answer = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		sum = new long[N+1][M+1];
		
		for(int[] a : arr) {
			Arrays.fill(a,  0);
		}
		
		for(int i=1; i<=N;i++) {
			String str = br.readLine();
			for(int j=1; j<=M;j++) {
				arr[i][j] = str.charAt(j-1) - '0';
			}
		}
		
		solve();
		System.out.println(answer);
	}
	
	public static void solve() {
		for(int i=1; i<=N;i++) {
			for(int j=1; j<=M;j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + (long)arr[i][j];
			}
		}
		
		// 6개의 경우 별로 곱의 합 구하기
		// 가로 3개
		for(int i=1; i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				long r1 = sum(1, 1, i, M);
				long r2 = sum(i+1, 1, j, M);
				long r3 = sum(j+1, 1, N, M);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
		
		// 세로 3개
		for(int i=1; i<M-1;i++) {
			for(int j=i+1;j<M;j++) {
				long r1 = sum(1, 1, N, i);
				long r2 = sum(1, i+1, N, j);
				long r3 = sum(1, j+1, N, M);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
		
		// ㅜ
		for(int i=1; i<N;i++) {
			for(int j=1;j<M;j++) {
				long r1 = sum(1, 1, i, M);
				long r2 = sum(i+1, 1, N, j);
				long r3 = sum(i+1, j+1, N, M);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
		
		// ㅗ
		for(int i=N;i>1;i--) {
			for(int j=1;j<M;j++) {
				long r1 = sum(i, 1, N, M);
				long r2 = sum(1, 1, i-1, j);
				long r3 = sum(1, j+1, i-1, M);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
		
		// ㅏ
		for(int i=1;i<M;i++) {
			for(int j=1;j<N;j++) {
				long r1 = sum(1, 1, N, i);
				long r2 = sum(1, i+1, j, M);
				long r3 = sum(j+1, i+1, N, M);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
		
		// ㅓ
		for(int i=M;i>1;i--) {
			for(int j=1;j<N;j++) {
				long r1 = sum(1, i, N, M);
				long r2 = sum(1, 1, j, i-1);
				long r3 = sum(j+1, 1, N, i-1);
				answer = Math.max(answer,  r1 * r2 * r3);
			}
		}
	}
	
	public static long sum(int r1, int c1, int r2, int c2) {
		return sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1];
	}

}
