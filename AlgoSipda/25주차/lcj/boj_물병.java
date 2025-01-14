import java.io.*;
import java.util.*;

public class Main_1052_물병 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N <= K) {
			System.out.println(0);
			return;
		}
		
		int buyCnt = 0;
		int cnt = 0;
		
		while(true) {
			int num = N;
			
			cnt = 0;
			while(num != 0) {
				// 2진수로 나타낼 때 나머지가 나올때마다 cnt + 1
				if(num % 2 == 1) cnt += 1;
				num /= 2;
			}
			
			if(cnt <= K) break;
			
			N += 1;
			buyCnt += 1;
		}
		System.out.println(buyCnt);
	}

}
