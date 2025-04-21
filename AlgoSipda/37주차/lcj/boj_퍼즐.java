import java.io.*;
import java.util.*;

public class Main_1525_퍼즐 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static String str;
	static int moveCnt = -1;
	
	static final String answer = "123456780";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		str = "";
		for(int i=0;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				str += st.nextToken();
			}
		}
		
		bfs();
		System.out.println(moveCnt);
	}
	
	public static void bfs() {
		Queue<String> q = new LinkedList<>();
		// 이전에 똑같은 판이 잇는지 확인하는 용
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put(str, 0);
		q.add(str);
		
		while(!q.isEmpty()) {
			String cur = q.poll();
			
			int zero = cur.indexOf('0');
			
			int r = zero/3;
			int c = zero%3;
			
			if(cur.equals(answer)) {
				moveCnt = map.get(answer);
				return;
			}
			
			for(int d = 0; d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(!isRange(nr, nc)) continue;
				int nextIdx = nr*3 + nc;
				char tmp = cur.charAt(nextIdx);
				
				// 위치 바꾸기
				String next = cur.replace(tmp, 'a');
				next = next.replace('0', tmp);
				next = next.replace('a', '0');
				
				if(!map.containsKey(next)) {
					q.add(next);
					map.put(next, map.get(cur)+1);
				}
			}
		}
	}

	public static boolean isRange(int r, int c) {
		return 0<=r && r<3 && 0<=c && c<3;
	}
}
