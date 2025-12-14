import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Lecture implements Comparable<Lecture> {
		int start, end;
		
		public Lecture(int start,int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Lecture o) {
			if(this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Lecture> lectures = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures.add(new Lecture(start, end));
		}
		
		Collections.sort(lectures);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int endTime = 0;
		for(int i=0;i<N;i++) {
			endTime = lectures.get(i).end;
			
			if(!pq.isEmpty() && pq.peek() <= lectures.get(i).start) {
				pq.poll();
			}
			pq.add(endTime);
		}
		System.out.println(pq.size());
	}
}