import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point{
	int r, c;

	public Point(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}

public class Main {

	static int[][] sudoku = new int[9][9];
	static char[][] sudoku2 = new char[9][9];
	static List<Point> zero = new ArrayList<>();
	static boolean end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0;i<sudoku.length;i++) {
			st = new StringTokenizer(br.readLine());
			sudoku2[i] = st.nextToken().toCharArray();
			for(int j=0;j<sudoku[i].length;j++) {
				sudoku[i][j] = sudoku2[i][j]-'0';
				if(sudoku[i][j]==0) {
					zero.add(new Point(i, j));
				}
			}
		}
		
		dfs(0);
		printSudoku();
	}

	private static void dfs(int idx) {
		if(idx == zero.size()) {
			end = true;
			return;
		}
		
		Point current = zero.get(idx);
		
		for(int i=1; i<=9;i++) {
			sudoku[current.r][current.c] = i;
			if(check3x3(current.r, current.c) && checkLength(current.r, current.c) && checkWidth(current.r, current.c)) {
				dfs(idx+1);
			}
			
			if(end) {
				return;
			}
			sudoku[current.r][current.c] = 0;
		}
	}
	
	//3x3 배열 검사하기
	private static boolean check3x3(int r, int c) {
		int sr = (r/3)*3;
		int sc = (c/3)*3;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(r != (sr+i) || c != (sc+j)) {
					if(sudoku[sr+i][sc+j] == sudoku[r][c])
						return false;
				}
			}
		}
		return true;
	}
	
	//세로 검사하기
	private static boolean checkLength(int r, int c) {
		for(int i=0;i<9;i++) {
			if(i != r) {
				if(sudoku[i][c] == sudoku[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//가로 검사하기
	private static boolean checkWidth(int r, int c) {
		for(int i=0;i<9;i++) {
			if(i != c) {
				if(sudoku[r][i] == sudoku[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printSudoku() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
