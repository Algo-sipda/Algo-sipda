import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] checked = new boolean[N+1][N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			checked[n1][n2] = true;			
		}
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(checked[i][k] && checked[k][j]) {
						checked[i][j] = true; 
					}
				}
			}
		}
		
		int[] cnt = new int[N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(checked[i][j]|| checked[j][i]) {
					cnt[i]++;
				}
			}
		}
		
		int answer = 0;
		for(int i=1;i<=N;i++) {
			if(cnt[i]==(N-1)) answer++;
		}
		System.out.println(answer);
	}
}
