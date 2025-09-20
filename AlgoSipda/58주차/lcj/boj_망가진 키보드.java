import java.io.*;

public class Main_6503_망가진키보드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int m = Integer.parseInt(br.readLine());
			
			if(m == 0) break;
			
			String str = br.readLine();
			
			int[] check = new int[128];
			int right = -1;
			int left = -1;
			int cnt = 0;
			int maxLen = 0;
			
			while(left <= right) {
				if(cnt < m) {
					// 포인터 사이 문자열 개수가 m보다 작을 때
					right++;
					char c = str.charAt(right);
					check[c]++;
					
					if(check[c] == 1) cnt++; // 처음 나온 애
				}
				else if(cnt == m && check[str.charAt(right+1)]>=1) {
					// m개까지 다 채웠는데 다음에 문자열 체크 후 다음에 또 할 수 있는지
					right++;
					check[str.charAt(right)]++;
				}
				else {
					left++;
					char c = str.charAt(left);
					check[c]--;
					
					if(check[c] == 0) cnt--;
				}
				
				maxLen = Math.max(maxLen, right-left);
				
				if(right == str.length()-1) break;
			}
			
			sb.append(maxLen).append("\n");
		}
		
		System.out.print(sb);
	}

}