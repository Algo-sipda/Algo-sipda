import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] ballon = new int[n];
		int[] arrow = new int[1000001];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			ballon[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for(int i=0;i<n;i++) {
			if(arrow[ballon[i]] > 0) {
				// 현재 높이의 풍선을 터뜨릴 수 있는 화살이 남아있다면
				arrow[ballon[i]]--;
				arrow[ballon[i]-1]++;
			}
			else {
				// 화살이 없다면
				cnt++;
				arrow[ballon[i]-1]++;
			}
		}
		
		System.out.println(cnt);
		
	}

}