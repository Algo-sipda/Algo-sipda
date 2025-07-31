import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 총 책의 개수
		int M = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 책의 개수
		
		ArrayList<Integer> negative = new ArrayList<>();
		ArrayList<Integer> positive = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
        
		for (int i=0; i<N; i++) {
			int book = Integer.parseInt(st.nextToken());
			if (book > 0) positive.add(book);
			else negative.add(book);
		}
		Collections.sort(negative);
		Collections.sort(positive, Collections.reverseOrder());
		
		
		ArrayList<Integer> distance = new ArrayList<>();
		// 음의 위치부터 책 정리
		while(!negative.isEmpty()) {
			int temp=0;
			temp = negative.remove(0);
			for (int i=1; i<M; i++) {
				if (!negative.isEmpty()) {
					negative.remove(0);
				}
			}
			distance.add(-temp);
		}
		
		// 양의 위치 책 정리
		while(!positive.isEmpty()) {
			int temp=0; 
			temp = positive.remove(0);
			for (int i=1; i<M; i++) {
				if (!positive.isEmpty()) {
					positive.remove(0);
				}	
			}
			distance.add(temp);
		}
		
		int result = 0;
		Collections.sort(distance);
		for (int i=0; i<distance.size(); i++) {
			if (i == distance.size()-1) result += distance.get(i);
			else result += (distance.get(i)*2);
		}
		
		System.out.println(result);
	}
}