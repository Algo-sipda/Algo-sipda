import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] board;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		
		for(int i=0;i<board.length;i++) {
			String s = br.readLine();
			for(int j=0;j<board[i].length;j++) {
				board[i][j]= s.charAt(j)-'0'; 
			}
		}
		cut(0, 0, N);
		System.out.println(sb);
	}

	private static void cut(int x, int y, int size) {
		int sum = 0;
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				sum += board[i][j];
			}
		}
		
		if(sum == (size*size)) {
			sb.append("1");
		}else if(sum == 0) {
			sb.append("0");
		}else {
			int half = size/2;
			sb.append("(");
			cut(x, y, half);
			cut(x, y+half, half);
			cut(x+half, y, half);
			cut(x+half, y+half, half);
			sb.append(")");
		}
	}
}
