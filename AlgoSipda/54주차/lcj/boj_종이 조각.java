import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] nums;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N][M];
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				nums[i][j] = s.charAt(j)-'0';
			}
		}
		
		findMaxNum();
		System.out.println(answer);
		
		br.close();
	}

	private static void findMaxNum() {
		for(int s=0;s<(1<<(N*M));s++) {
			int sum = 0;
			//가로 찾기
			for(int i=0;i<N;i++) {
				int current = 0;
				for(int j=0;j<M;j++) {
					int k = i*M+j;
					if((s&(1<<k))==0) {	// s의 k번째 비트가 0일 경우 -> 해당 숫자 : 가로
						current *= 10;
						current += nums[i][j];
					}else {					// 해당 숫자는 세로
						sum += current;
						current = 0;
					}
				}
				sum += current;
			}
			
			//세로 찾기
			for(int i=0;i<M;i++) {
				int current = 0;
				for(int j=0;j<N;j++) {
					int k = j*M+i;
					if((s&(1<<k))!=0) {	//s의 k번째 비트가 0이 아닐(1일 경우) 경우 -> 해당 숫자 :세로
						current*= 10;
						current += nums[j][i];
					}else {
						sum += current;
						current = 0;
					}
				}
				sum += current;
			}
			answer = Math.max(answer, sum);
		}
	}
}
