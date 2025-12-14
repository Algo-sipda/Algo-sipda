import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		// 최소값 구하는 dp 계산
		Long[] minDp = new Long[101];
		Arrays.fill(minDp, Long.MAX_VALUE);
		minDp[2] = 1L;
		minDp[3] = 7L;
		minDp[4] = 4L;
		minDp[5] = 2L;
		minDp[6] = 6L;
		minDp[7] = 8L;
		minDp[8] = 10L;
		
		// 0으로 숫자가 시작할 수 없기 때문에 배열 하나 더 추가
		String[] arr = {"1", "7", "4", "2", "0", "8"};
		
		for(int i=9;i<=100;i++) {
			for(int j=2;j<=7;j++) {
				String numS = ""+minDp[i-j] + arr[j-2];
				minDp[i] = Math.min(minDp[i], Long.parseLong(numS));
			}
		}
		
		// 테스트 케이스 시작
		for(int T = 0; T<tc;T++) {
			int cnt = Integer.parseInt(br.readLine());
			
			// 최대값 구하기
			StringBuilder max = new StringBuilder();
			if(cnt%2 == 1) max.append("7");
			else max.append("1");
			
			for(int i=1;i<cnt/2;i++) max.append("1");
			
			// 출력
			sb.append(minDp[cnt]).append(" ").append(max).append("\n");
		}
		
		System.out.print(sb);
	}

}