import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;tc++) {
			N = Integer.parseInt(br.readLine());
			dfs(1, 1, 1, 0, "1");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int now, int num, int sign, int answer, String str) {
		if(now==N) {
			answer = answer + (num*sign);
			if(answer == 0) {
				sb.append(str).append("\n");
			}
			return;
		}
		
		// 공백일 경우
		dfs(now+1, num*10+(now+1), sign, answer, str+" "+(now+1));
		dfs(now+1, now+1, 1, answer+(num*sign), str+"+"+(now+1));
		dfs(now+1, now+1, -1, answer+(num*sign), str+"-"+(now+1));
	}
}