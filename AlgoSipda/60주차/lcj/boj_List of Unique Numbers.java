import java.io.*;
import java.util.*;

/**
 * 
 * 문제 풀이: 투포인터
 * 1 / 2 / 3 / 1 / 2 / 1 2 / 2 3 / 3 1/ 1 2 / 1 2 3 / 2 3 1 / 3 1 2
 * 포인터 start: 1, end: 3
 * => 2 3 1 -> 2 23 231 => (end - start + 1)
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[] visited = new boolean[(int)1e5+1];
		long cnt = 0;
		for(int start = 0, end = -1; start < N; start++) {
			while(end+1 < N) {
				if(visited[arr[end+1]]) break;
				
				visited[arr[end+1]] = true;
				end++;
			}
			cnt += (end - start+1);
			visited[arr[start]] = false;
			
		}
		System.out.println(cnt);
	}

}
