import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static char[] word1, word2, word3;
	static String answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			word3 = st.nextToken().toCharArray();
			
			answer = "no";
			bfs();
			
			sb.append("Data set "+tc+": "+answer+"\n");
		}
		System.out.print(sb);
	}

	private static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[word1.length+1][word2.length+1];
		
		q.add(new Point(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int Idx1 = p.x;
			int Idx2 = p.y;
			
			if(Idx1 + Idx2 == word3.length) {
				answer = "yes";
				break;
			}
			if(Idx1 < word1.length && word3[Idx1 + Idx2] == word1[Idx1] && !visited[Idx1+1][Idx2]) {
				visited[Idx1+1][Idx2] = true;
				q.add(new Point(Idx1+1, Idx2));
			}
			if(Idx2 < word2.length && word3[Idx1 + Idx2] == word2[Idx2] && !visited[Idx1][Idx2+1]) {
				visited[Idx1][Idx2+1] = true;
				q.add(new Point(Idx1, Idx2+1));
			}
		}
	}
}