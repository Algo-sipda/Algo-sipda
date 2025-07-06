import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, d, k, c;
	static int[] dish, visited;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());	// 접시의 수(max : 3,000,000)
		d = Integer.parseInt(st.nextToken());	// 초밥의 가짓 수
		k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		dish = new int[N];
		visited = new int[d+1];
		
		for(int i=0;i<N;i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		susi();
		System.out.println(answer);
	}

	private static void susi() {
		// 0번째 접시부터 먹는 거 체크
		int total = 0;
		int max = 0;
		
		for(int i=0;i<k;i++) {
			if(visited[dish[i]]==0) {//처음 먹는 초밥
				total++;
			}
			visited[dish[i]]++;
		}
		
		if(visited[c] == 0) max = total + 1;
	    else max = total;
		
		for(int i=1; i<N; i++) {
	        visited[dish[i - 1]]--;
	        if(visited[dish[i - 1]] == 0) total--;

	        int next = dish[(i + k - 1) % N];
	        
	        if(visited[next] == 0) total++;
	        visited[next]++;

	        if(visited[c] == 0) max = Math.max(max, total + 1);
	        else max = Math.max(max, total);
	    }
		answer = max;
	}
}
