import java.io.*;
import java.util.*;

/**
 * AC는 정수 배열에 연산을 하기 위해 만든 언어
 * 
 * @author lcj52
 *
 */


public class Main_5430_AC {
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T;tc++) {
			String p = br.readLine();
			
			int n = Integer.parseInt(br.readLine());
			
			String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
			
			sb.append(acFunc(p, n, arr));
		}
		
		System.out.println(sb);
	}

	private static String acFunc(String p, int n, String[] arr) {
		Deque<Integer> dq = new ArrayDeque<>();
		
		for(int i=0;i<n;i++) {
			dq.add(Integer.parseInt(arr[i]));
		}
		
		boolean dir = true;
		
		for(int i=0;i<p.length();i++) {
			char type = p.charAt(i);
			
			if(type == 'R') {
				dir = !dir;
			}
			else {
				if(dq.isEmpty()) {
					return "error\n";
				}
				
				if(dir) {
					dq.pollFirst();
				}
				else {
					dq.pollLast();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		while(!dq.isEmpty()) {
			if(dir) {
				sb.append(dq.poll());
			}
			else {
				sb.append(dq.pollLast());
			}
			
			if(!dq.isEmpty())
				sb.append(",");
		}
		sb.append("]\n");
		return sb.toString();
	}
}