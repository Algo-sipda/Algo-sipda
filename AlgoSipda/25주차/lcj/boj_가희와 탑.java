package B24000;

import java.io.*;
import java.util.*;

public class Main_24337_가희와탑 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int maxHeight = Math.max(a, b);
		
		Deque<Integer> q = new LinkedList<Integer>();
		for(int i=1; i<a;i++) {
			q.add(i);
		}
		
		q.add(maxHeight);
		
		for(int i=b-1;i>0;i--) {
			q.add(i);
		}
		
		if(q.size() > N) {
			System.out.println(-1);
			return;
		}
		
		int first = q.pollFirst();
		int size = q.size();
		
		for(int i=1;i<N-size;i++) {
			q.addFirst(1);
		}
		
		q.addFirst(first);
		for(int i=1; i<=N;i++) {
			sb.append(q.pollFirst()+" ");
		}
		System.out.println(sb);
	}

}
