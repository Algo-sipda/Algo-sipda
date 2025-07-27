import java.io.*;
import java.util.StringTokenizer;

public class Main_2828_사과담기게임 {
	
	static int N, M, J;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		J = Integer.parseInt(br.readLine());
		
		int dist = 0;
		int start = 1;
		int end = M;
		
		for(int i=0;i<J;i++) {
			int apple = Integer.parseInt(br.readLine());
			
			if(start > apple) {
				dist += (start-apple);
				end -= (start-apple);
				start = apple;
			}
			else if(end < apple) {
				dist += (apple-end);
				start += (apple-end);
				end = apple;
			}
		}
		System.out.println(dist);
	}

}