import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if(this.x == o.x) return this.y - o.y;
			return this.x - o.x;
		}
	}

	static int N, M, L, K;
	static Point[] stars;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stars = new Point[K];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(stars);
		
		int max = 0;
		for(int i=0;i<K;i++) {
			for(int j=0;j<K;j++) {
				max = Math.max(max, countStars(stars[i].x, stars[j].y));
			}
		}
		
		System.out.println(K-max);
	}

	private static int countStars(int x, int y) {
		int cnt = 0;
		for(Point star:stars) {
			if(star.x >= x && star.y >= y 
					&& star.x <= x + L && star.y <= y + L)
				cnt++;
		}
		return cnt;
	}

}
