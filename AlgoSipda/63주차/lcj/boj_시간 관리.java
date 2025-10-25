import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Time implements Comparable<Time> {
		int t, s;
		
		public Time(int t, int s) {
			this.t = t;
			this.s = s;
		}

		@Override
		public int compareTo(Time o) {
			return o.s - this.s;
		}
	}
	
	static PriorityQueue<Time> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			pq.add(new Time(t, s));
		}
		
		int answer = pq.peek().s+1;
		
		while(!pq.isEmpty()) {
			Time t = pq.poll();
			
			if(t.s < answer) {
				answer = t.s;
			}
			
			answer -= t.t;
			
			if(answer < 0) {
				answer = -1;
				break;
			}
		}
		System.out.println(answer);
	}

}