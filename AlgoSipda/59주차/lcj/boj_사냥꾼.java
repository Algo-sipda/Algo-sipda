import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
	public static int M, N;
	public static long L;
	public static int[] range;
	public static Point[] animals;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Long.parseLong(st.nextToken());
		
		range = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			range[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(range);
		
		animals = new Point[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			animals[i] = new Point(x, y);
		}
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			// 동물들을 잡을 수 있는 가까운 사대 부대 찾기
			answer += search(i);
		}
		System.out.println(answer);
	}

	public static int search(int idx) {
		int start = 0;
		int end = M;
		int mid = 0;
		
		while(start <= end) {
			mid = (start + end)/2;
			if(mid >= M) return 0;
			
			int dist = getDist(animals[idx].x, animals[idx].y, range[mid]);
			
			if(L < dist && animals[idx].x < range[mid]) {
				end = mid - 1;
			}
			else if(L < dist && animals[idx].x >= range[mid]) {
				start = mid + 1;
			}
			else if(L >= dist) {
				return 1;
			}
		}
		return 0;
	}
	
	public static int getDist(int x, int y, int ran) {
		return Math.abs(ran - x) + y;
	}
}
