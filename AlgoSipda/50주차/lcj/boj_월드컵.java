import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] group;
	static boolean[][] visited;
	static int teamCnt = 6;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int testcase=1;testcase<=4;testcase++) {
			int sum = 0;
			group = new int[teamCnt][3];
			visited = new boolean[teamCnt][teamCnt];
			
			st = new StringTokenizer(br.readLine()," ");
			
			int sumWin = 0, sumLose = 0;
			boolean flag = true;
			for(int i=0;i<group.length;i++) {
				int oneTeamSum = 0;
				for(int j=0;j<group[i].length;j++) {
					group[i][j]= Integer.parseInt(st.nextToken());
					sum += group[i][j];
					oneTeamSum += group[i][j];
				}
				visited[i][i]= true;//본인 것 
				if(oneTeamSum != 5) {
					flag = false;
					break;
				}
				sumWin += group[i][0];
				sumLose += group[i][2];
			}
			if(sum != 30 || !flag || sumWin != sumLose) sb.append("0 ");
			else {
				if(dfs(0)) {
					sb.append("1 ");
				}else {
					sb.append("0 ");
				}
			}
		}
		System.out.println(sb);
	}
	
	private static boolean dfs(int idx) {
		
		for(int i = 0;i<3;i++) {
			if(group[idx][i]>0) {
				for(int j=0;j<teamCnt;j++) {
					if(!visited[idx][j]&& group[j][2-i]>0) {
						visited[idx][j]= true; 
						group[idx][i] -= 1;
						group[j][2-i] -= 1;
						if(dfs(idx)) return true; 
						visited[idx][j]= false; 
						group[idx][i] += 1;
						group[j][2-i] += 1;
					}
				}
			}
		}
	
		if(idx==(teamCnt-1)) {
			for(int i=0;i<teamCnt;i++) {
				for(int j=0;j<3;j++) {
					if(group[i][j]!= 0 ) return false;
				}
			}
			return true;
		}else {
			for(int i=0;i<3;i++) {
				if(group[idx][i] != 0) return false;
			}
			return dfs(idx+1);
		}
	}

}
