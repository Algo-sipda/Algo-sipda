import java.io.*;
import java.util.*;

public class Main_2116_주사위쌓기 {
	
	static int N;
	static int[][] dice;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<6;j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sum = 0;
		int answer = 0;
		
		for(int i=0;i<6;i++) {
			sum = 0;
			int bottom = dice[0][i];
			int top = dice[0][setTop(i)];
			
			for(int j = 0; j<N;j++) {
				int max = 0;
				for(int k=0;k<6;k++) {
					if(dice[j][k] == top) {
						bottom = top;
						top = dice[j][setTop(k)];
						
						max = findMax(bottom, top);
						break;
					}
				}
				sum += max;
			}
			answer = Math.max(answer, sum);
		}

		System.out.println(answer);
	}

	public static int setTop(int idx) {
		if(idx == 0) return 5;
		else if(idx == 1) return 3;
		else if(idx == 2) return 4;
		else if(idx == 3) return 1;
		else if(idx == 4) return 2;
		else return 0;
	}
	
	public static int findMax(int bottom, int top) {
		int max = 0;
		
		for(int i=1;i<=6;i++) {
			if(i == bottom || i == top) continue;
			max = Math.max(max,  i);
		}
		
		return max;
	}
}