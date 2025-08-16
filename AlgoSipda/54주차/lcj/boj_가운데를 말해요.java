import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> min = new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
			
			//짝수인 경우
			if(max.size() == min.size()) {
				max.add(num);
			}
			//홀수인 경우
			else {
				min.add(num);
			}
			
			if(!min.isEmpty() && max.peek()>min.peek()) {
				int tmp = max.poll();
				max.add(min.poll());
				min.add(tmp);
			}
			sb.append(max.peek()).append("\n");
		}
		System.out.print(sb);
	}

}
