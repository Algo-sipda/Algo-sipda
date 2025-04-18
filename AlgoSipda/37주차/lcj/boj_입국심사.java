import java.io.*;
import java.util.*;

public class Main_3079_입국심사 {
	
	static int N;
	static long M, answer = Long.MAX_VALUE;// M의 범위 : 10억
	static long[] immigration; // 이거의 범위: 10억

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		immigration = new long[N];
		long max = 0;
		
		for(int i=0;i<N;i++) {
			immigration[i] = Long.parseLong(br.readLine());
			max = Math.max(max, immigration[i]);
		}
		
		Arrays.sort(immigration);
		
		long start = 0;
		long end = max * M; // 가장 오래 걸리는 입국심사대 * M명
		
		while(end >= start) {
			long mid = (end + start) / 2;
			
			long sum = 0;
			
			for(int i=0;i<N;i++) {
				long cnt = mid / immigration[i];// 한 계산대에서 맡을 수 있는 사람의 수
				
				if(sum >= M) break;
				sum += cnt;
			}
			
			if(sum >= M) {
				end = mid-1;
				answer = Math.min(answer, mid);
			}
			else {
				start = mid+1;
			}
		}
		System.out.println(answer);
	}

}