import java.io.*;
import java.util.*;

public class Main_14890_경사로 {

	static int N, L;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		for(int i=0;i<N;i++) {
			if(putRunway(i, 0, true)) cnt++;
			if(putRunway(0, i, false)) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	public static boolean putRunway(int r, int c, boolean isRow) {
		int[] height = new int[N];
		boolean[] isRunway = new boolean[N];
		
		for(int i=0;i<N;i++) {
			if(isRow)
				height[i] = board[r][c+i];
			else
				height[i] = board[r+i][c];
		}
		
		for(int i=0;i<N-1;i++) {
			int diff = height[i] - height[i+1];
			// 2이상 차이 -> 경사로 세울 수 있음
			if(Math.abs(diff)>=2) return false;
			
			// 0일 경우 -> 그냥 지나가기
			if(diff == 0) continue;
			
			// 내리막
			if(diff == 1) {
				for(int j=i+1;j<=i+L;j++) {
					if(!isRange(j) || height[i+1] != height[j] || isRunway[j])
						return false;
					isRunway[j] = true;
				}
				i += (L-1);
			}
			else if(diff == -1) {
				for(int j=i;j>i-L;j--) {
					if(!isRange(j) || height[i] != height[j] || isRunway[j])
						return false;
					isRunway[j] = true;
				}
			}
		}
		
		return true;
	}

	public static boolean isRange(int point) {
		return 0<=point && point<N;
	}
}