import java.io.*;
import java.util.*;

public class Main_2536_버스갈아타기 {
	
	static class Bus {
		int idx, x1, y1, x2, y2, dir;	// dir -> 0: 수평, 1: 수직
		
		public Bus(int idx, int x1, int y1, int x2, int y2, int dir) {
			this.idx = idx;
			this.x1 = Math.min(x1, x2);
			this.y1 = Math.min(y1, y2);
			this.x2 = Math.max(x1, x2);
			this.y2 = Math.max(y1, y2);
			this.dir = dir;
		}
		
		public boolean isContain(int x, int y) {
			// 수평 + 같은 x 값(수평이니까 값 하나만 확인) + y 값이 범위 안에 있는지
			if(this.dir == 0 && this.x1 == x && this.y1 <= y && y <= this.y2)
				return true;
			// 수직 + 같은 y 값 + x 값이 범위 안에 있는지
			if(this.dir == 1 && this.y1 == y && this.x1 <= x && x <= this.x2)
				return true;
			return false;
		}
		
		public boolean isContain(Bus b) {
			// 두개 모두 수평인 경우
			if(this.dir == 0 && b.dir == 0) {
				// x값이 다를 경우 이용 x
				if(this.x1 != b.x1) return false;
				// y값이 범위 안에 없는 경우
				if(this.y1 > b.y2 || this.y2 < b.y1) return false;
				return true;
			}
			// 두개 모두 수직인 경우
			else if(this.dir == 1 && b.dir == 1) {
				// y값이 다를 경우 이용 x
				if(this.y1 != b.y1) return false;
				// x값이 범위 안에 없는 경우
				if(this.x1 > b.x2 || this.x2 < b.x1) return false;
				return true;
			}
			//b는 수직, this는 수평인 경우
			else if(this.dir == 0 && b.dir == 1) {
				if(b.x1 <= this.x1 && this.x1 <= b.x2 && this.y1 <= b.y1 && b.y1 <= this.y2) return true;
				return false;
			}
			// b는 수평, this.는 수직인 경우
			else {
				if(this.x1 <= b.x1 && b.x1 <= this.x2 && b.y1 <= this.y1 && this.y1 <= b.y2) return true;
				return false;
			}
		}
	}
	
	static int m, n, k;
	static int sx, sy, ex, ey;
	static int answer = 0;
	static Bus[] buses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		buses = new Bus[k+1];
		for(int i=0;i<k;i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			if(x1 == x2) {
				buses[idx] = new Bus(idx, x1, y1, x2, y2, 0);
			}
			else {
				buses[idx] = new Bus(idx, x1, y1, x2, y2, 1);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		Queue<Bus> q = new LinkedList<>();
		int[] cntBus = new int[k+1];
		
		for(int i=1;i<=k;i++) {
			if(buses[i].isContain(sx, sy)) {
				q.add(buses[i]);
				cntBus[i] = 1;
			}
		}
		
		while(!q.isEmpty()) {
			Bus cur = q.poll();
			
			if(cur.isContain(ex, ey)) {
				answer = cntBus[cur.idx];
				return;
			}
			
			for(int i=1;i<=k;i++) {
				if(cntBus[i] != 0 || !cur.isContain(buses[i])) continue;
				
				q.add(buses[i]);
				cntBus[i] = cntBus[cur.idx] + 1;
			}
		}
	}
}