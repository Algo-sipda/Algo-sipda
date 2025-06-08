import java.util.*;
import java.io.*;


class Solution {
	private static class Atom {
		int x, y, dir, energy;
		
		public Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
	}
	
	private static int n, answer;
	private static List<Atom> atom;
	private static int[][] map = new int[4001][4001];
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st = null;
        
		int T = Integer.parseInt(br.readLine());
		
		atom = new ArrayList<>();

		for(int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			answer = 0;
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)*2;
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				
				atom.add(new Atom(x, y, dir, energy));
				map[x][y] = energy;
			}

			answer = solve();
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int solve() {
		int sum = 0;
		
		while(!atom.isEmpty()) {
			for(int i=0;i<atom.size();i++) {
				Atom a = atom.get(i);
				
				map[a.x][a.y] = 0;
				a.x += dx[a.dir];
				a.y += dy[a.dir];
				
				if(!isRange(a.x, a.y)) {
					atom.remove(i);
					i--;
					continue;
				}
				
				map[a.x][a.y] += a.energy;
			}
			
			for(int i=0;i<atom.size();i++) {
				Atom a = atom.get(i);
				
				if(map[a.x][a.y] != a.energy) {
					sum += map[a.x][a.y];
					map[a.x][a.y] = 0;
					atom.remove(i);
					i--;
				}
			}
		}
		
		return sum;
	}
	
	public static boolean isRange(int x, int y) {
		return 0<= x && x<= 4000 && 0<= y && y<= 4000;
	}
}