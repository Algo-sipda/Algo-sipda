import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static ArrayList<ArrayList<Integer>> friends = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> enemies = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		for(int i=0; i<=n;i++) {
			friends.add(new ArrayList<>());
			enemies.add(new ArrayList<>());
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			if(type.equals("F")) {
				friends.get(f1).add(f2);
				friends.get(f2).add(f1);
			}else {
				enemies.get(f1).add(f2);
				enemies.get(f2).add(f1);
			}
		}
		
		// 원수 관계에서 친구 찾기
		for(int i=1; i<=n;i++) {
			visited = new boolean[n+1];
			findFriendsFromEnemies(i, i, 0);
		}
		
		visited = new boolean[n+1];
		int cnt = 0;
		for(int i=1;i<=n;i++) {
			if(visited[i]) continue;
			
			cnt++;
			checkTeam(i);
		}
		System.out.println(cnt);
	}
	
	public static void checkTeam(int idx) {
		if(visited[idx]) return;
		
		visited[idx] = true;
		for(int friend: friends.get(idx)) {
			checkTeam(friend);
		}
	}

	public static void findFriendsFromEnemies(int start, int idx, int depth) {
		if(visited[idx]) return;
		
		if(depth == 2) {
			friends.get(start).add(idx);
			return;
		}
		
		for(int i: enemies.get(idx)) {
			findFriendsFromEnemies(start, i, depth+1);
		}
	}
}
