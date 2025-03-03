import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[][] map = new boolean[101][101];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dragonCurve(x, y, d, g);
		}
		
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
	
	private static void dragonCurve(int x, int y, int d, int g) {
		ArrayList<Integer> dir = new ArrayList<>();
		
		dir.add(d);
		for(int i=0;i<g;i++) {
			for(int j=dir.size()-1;j>=0;j--) {
				dir.add((dir.get(j)+1)%4);
			}
		}
		
		map[y][x] = true;
		
		for(int i=0;i<dir.size();i++) {
			x += dx[dir.get(i)];
			y += dy[dir.get(i)];
			map[y][x] = true;
		}
		
	}

}