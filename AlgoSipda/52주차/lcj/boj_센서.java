import java.io.*;
import java.util.*;

/**
 * 센서의 개수 : N
 * 집중국 개수 : K
 * 센서의 좌표 : N개 -> 최대값 1_000_000
 * 
 * @author lcj52
 *
 */

public class Main_2212_센서 {
	
	static int N, K;
	static int[] sensor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		sensor = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sensor);
		
		// 각 센서의 거리의 차이를 담은 배열
		Queue<Integer> q = new PriorityQueue<Integer>();
		for(int i=0;i<N-1;i++) {
			q.add(sensor[i+1]-sensor[i]); 
		}
		
		int sum = 0;
		for(int i=0;i<N-K;i++) {
			sum += q.poll();
		}
		
		System.out.println(sum);
	}

}