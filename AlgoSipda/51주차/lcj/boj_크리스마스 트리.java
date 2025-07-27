import java.io.*;
import java.util.*;

public class Main_1234_크리스마스트리 {
	
	static int N;
	static int red, green, blue;
	
	static long[] num;
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		red = Integer.parseInt(st.nextToken());
		green = Integer.parseInt(st.nextToken());
		blue = Integer.parseInt(st.nextToken());
		
		num = new long[11];
		
		for(int i=1;i<=10;i++) {
			num[i] = calc(i);
		}
		
		solve(1, 0, 0, 0, 1);
		
		System.out.println(answer);
	}
	
	private static int calc(int n) {
		int result = 1;
		for(int i=1;i<=n;i++) {
			result *= i;
		}
		return result;
	}

	private static void solve(int depth, int r, int g, int b, long cur) {
		// 장난감 다 쓴 경우
		if(r > red || g > green || b > blue) return;
		
		// 목표 레벨 도달
		if(depth == (N+1)) {
			answer += cur;
			return;
		}
		
		// 한 가지
		solve(depth+1, r+depth, g, b, cur);
		solve(depth+1, r, g+depth, b, cur);
		solve(depth+1, r, g, b+depth, cur);
		
		// 두 가지
		if(depth % 2 == 0) {
			int cnt = depth/2;
			
			long tmp = comb(depth, cnt, 0);
			solve(depth+1, r+cnt, g+cnt, b, cur*tmp);
			solve(depth+1, r+cnt, g, b+cnt, cur*tmp);
			solve(depth+1, r, g+cnt, b+cnt, cur*tmp);	
		}
		
		// 세 가지
		if(depth % 3 == 0) {
			int cnt = depth/3;
			
			long tmp = comb(depth, cnt, 1);
			solve(depth+1, r+cnt, g+cnt, b+cnt, cur*tmp);
		}
	}
	
	private static long comb(int depth, int cnt, int type) {
		if(type == 0) {
			return num[depth]/(num[cnt]*num[cnt]);
		}
		else {
			return num[depth]/(num[cnt]*num[cnt]*num[cnt]);
		}
	}
}