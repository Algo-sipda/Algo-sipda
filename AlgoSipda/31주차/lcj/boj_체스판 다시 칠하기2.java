import java.io.*;
import java.util.*;

public class Main_25682_체스판다시칠하기2 {

	static int N, M, K;
	static char[][] chess;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		chess = new char[N][M];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				chess[i][j] = str.charAt(j);
			}
		}
		
		// 첫번째 타일 검정색 시작
		int[][] prefixBlack = prefixSumChess('B');
		// 두번째 타일 흰색 시작
		int[][] prefixWhite = prefixSumChess('W');
		
		int minBlack = cutChessBoard(prefixBlack);
		int minWhite = cutChessBoard(prefixWhite);
//		printMap(prefixBlack);
//		printMap(prefixWhite);
		System.out.println(Math.min(minBlack, minWhite));
	}
	
	public static void printMap(int[][] map) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
	
	public static int[][] prefixSumChess(char color) {
		int[][] prefix = new int[N+1][M+1];
		
		for(int i=0;i<N;i++) {
			for(int j=0; j<M;j++) {
				int curColor = 0;
				if((i+j)%2 == 0) {
					curColor = chess[i][j] == color ? 0 : 1;
				}
				else {
					curColor = chess[i][j] == color ? 1 : 0;
				}
				prefix[i+1][j+1] = prefix[i][j+1] + prefix[i+1][j]-prefix[i][j] + curColor;
			}
		}
		
		return prefix;
	}
	
	public static int cutChessBoard(int[][] prefix) {
		int cnt = Integer.MAX_VALUE;
		
		for(int i=1;i<=N-K+1;i++) {
			for(int j=1;j<=M-K+1;j++) {
				int curCnt = prefix[i+K-1][j+K-1]-prefix[i-1][j+K-1]-prefix[i+K-1][j-1] + prefix[i-1][j-1];
				cnt = Math.min(cnt, curCnt);
			}
		}
		return cnt;
	}
}