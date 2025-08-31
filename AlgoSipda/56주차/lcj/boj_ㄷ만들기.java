import java.io.*;
import java.util.*;

public class Main_23562_ㄷ만들기 {

	static int n, m, a, b;
	static char[][] board;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		for(int i=0;i<n;i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		int minLen = Math.min(n, m)/3;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				for(int k=1;k<=minLen;k++) {
					if(i+k*3-1 >= n || j+k*3-1 >= m)
						break;
					
					min = Math.min(min, makeD(i, j, k));
				}
			}
		}
		
		System.out.println(min);
	}
	
	private static int makeD(int r, int c, int k) {
		int sum = 0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(i>=r && j>=c && i<r+3*k && j<c+3*k) {
					if(i >= r+k && i<r+2*k && j>=c+k) {
						if(board[i][j] == '#')
							sum += b;
					}
					else {
						if(board[i][j] == '.')
							sum += a;
					}
				}
				else {
					if(board[i][j] == '#') {
						sum += b;
					}
				}
			}
		}
		
		return sum;
	}

}