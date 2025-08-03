import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long B;
	static int[][] matrix;
	static final int MOD = 1000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		matrix = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] answer = solve(B);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sb.append(answer[i][j]%MOD).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	private static int[][] solve(long cnt){
		if(cnt == 1) {
			return matrix;
		}
		
		int[][] answer = solve(cnt/2);
		//짝수
		if(cnt % 2 == 0) {
			return calc(answer, answer);
		}
		//홀수
		else {
			int[][] answer2 = calc(answer, matrix);
			return calc(answer2, answer);
		}
		
	}
	
	private static int[][] calc(int[][] m1, int[][] m2){
		int[][] tmp = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int sum = 0;
				for(int k=0;k<N;k++) {
					sum = (sum + m1[i][k] * m2[k][j])%MOD;
				}
				tmp[i][j] = sum;
			}
		}
		return tmp;
	}
}
