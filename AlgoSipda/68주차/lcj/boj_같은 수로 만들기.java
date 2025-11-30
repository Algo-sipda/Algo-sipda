import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		long answer = 0;
		long max = 0;
		Stack<Long> st = new Stack<>();
		
		for(int i=0;i<n;i++) {
			long cur = Long.parseLong(br.readLine());
			
			max = Math.max(max, cur);
			
			if(!st.isEmpty()) {
				long top = st.pop();
				
				if(top <= cur) {
					answer += (cur - top);
				}
			}
			st.push(cur);
		}
		
		long last = st.pop();
		answer += (max - last);
		
		System.out.println(answer);
	}
}
