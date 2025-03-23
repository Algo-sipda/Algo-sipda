import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Taxi {
	int r, c;

	public Taxi(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

class Customer implements Comparable<Customer> {
	int num;
	int sr, sc;
	int er, ec;

	public Customer(int num, int sr, int sc, int er, int ec) {
		super();
		this.num = num;
		this.sr = sr;
		this.sc = sc;
		this.er = er;
		this.ec = ec;
	}

	@Override
	public int compareTo(Customer o) {
		if (this.sr == o.sr)
			return this.sc - o.sc;
		else
			return this.sr - o.sr;
	}

}

public class Main {
	
	static int n, m, fuel;
	static int[][] map;
	static Customer[] customer;
	static Taxi taxi;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int custCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		customer = new Customer[m+1];
		
		for(int i=1; i<=n;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {//벽이면 -1로 표시하자
					map[i][j] = -1;
				}
			}
		}
		st = new StringTokenizer(br.readLine()," ");
		taxi = new Taxi(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		
		//고객 정보
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			customer[i] = new Customer(i, sr, sc, er, ec);
			map[sr][sc] = i;//출발지에 택시번호 붙이기
		}
		
		for(int i=0;i<m;i++) {
			drive();
		}
		if(custCnt != m)
			System.out.println(-1);
		else
			System.out.println(fuel);
	}

	private static void drive() {
		int tr = taxi.r;
		int tc = taxi.c;
		
		if(map[tr][tc] > 0) {
			Customer cur = customer[map[tr][tc]];
			arrive(cur);
			return;
		}
		
		boolean[][] visited = new boolean[n+1][n+1];
		PriorityQueue<Customer> list = new PriorityQueue<>();
		Queue<Taxi> q = new LinkedList<>();
		q.add(new Taxi(tr, tc));
		visited[tr][tc] = true;
		int dist = 1;
		while(q.size() != 0) {
			int len = q.size();
			
			for(int i=0;i<len;i++) {
				Taxi cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				for(int k=0;k<4;k++) {
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					if(nr<=0 || nc<=0 || nr>n || nc >n) continue;
					if(map[nr][nc] == -1) continue;
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					
					if(map[nr][nc] == 0)
						q.add(new Taxi(nr, nc));
					else if(map[nr][nc]>0)
						list.add(customer[map[nr][nc]]);
				}
			}
			if(list.size() != 0) break;
			dist++;
		}
		if(list.size() == 0) return;//승객 못 태움
		if(fuel - dist < 0 ) return;//연료 부족
		fuel -= dist;
		arrive(list.poll());//가장 가까운 고객 정보
	}
	
	private static void arrive(Customer cur) {
		boolean[][] visited = new boolean[n+1][n+1];
		Queue<Taxi> q = new LinkedList<>();
		q.add(new Taxi(cur.sr, cur.sc));
		map[cur.sr][cur.sc] = 0;
		
		int cost = 1;
		while(q.size() != 0) {
			int len = q.size();
			if(cost>fuel) {
				System.out.println(-1);
				System.exit(0);
			}
			
			for(int p=0;p<len;p++) {
				Taxi t = q.poll();
				int r = t.r;
				int c = t.c;
				
				for(int d = 0; d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr<=0 || nc <= 0|| nr>n || nc > n) continue;
					
					if(visited[nr][nc]) continue;
					if(map[nr][nc] == -1) continue;
					if(nr == cur.er && nc == cur.ec) {
						custCnt++;
						taxi.r = nr;
						taxi.c = nc;
						fuel += cost;
						return;
					}else {
						q.add(new Taxi(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
			cost += 1;
		}
	}
}