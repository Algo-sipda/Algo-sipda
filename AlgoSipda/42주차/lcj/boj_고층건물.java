import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] building;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		building = new int[N];
		visited = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			building[i] = Integer.parseInt(st.nextToken());
		}
		count();
		Arrays.sort(visited);
		System.out.println(visited[N-1]);
	}

	private static void count() {
		for(int i=0;i<N-1;i++) {
			visited[i] += 1;
			visited[i+1] += 1;
			double slope = building[i+1] - building[i];
			
			for(int j = i+2 ; j<N;j++) {
				double nSlope = (double)(building[j] - building[i])/(j-i);
				if(nSlope <= slope) continue;
				slope = nSlope;
				visited[i]++;
				visited[j]++;
			}
		}
	}
}