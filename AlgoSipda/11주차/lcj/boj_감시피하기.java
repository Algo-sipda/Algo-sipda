import java.util.Scanner;

public class Main {
	
	static int N;
	static char[][] map;
	static int moveR[] = { -1, 1, 0, 0 }; 
	static int moveC[] = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new char[N][N];

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				String s = sc.next();
				map[i][j] = s.charAt(0);
			}
			
		}
		
		dfs(0, -1, 0);
		
		if(answer == 1)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	static void dfs(int r, int c, int cnt) {
		if(cnt == 3) {
			boolean yes = true;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != 'T')
						continue;
					
					// 감시 가능한 학생이 존재하는지 
					boolean sw = findS(i, j);
					// 감시 가능 
					if(sw == true) {
						yes = false;
						break;
					}
				}
				// 감시 가능 
				if(yes == false)
					break;
			}
			// 감시 불가 
			if(yes == true)
				answer = 1;
			return;
		}
		
		for(int i=c+1; i<N; i++) {
			if(map[r][i] != 'X')
				continue;
			
			map[r][i] = 'O';
			dfs(r, i, cnt+1);
			map[r][i] = 'X';
		}
		
		for(int i=r+1; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 'X')
					continue;
				map[i][j] = 'O';
				dfs(i, j, cnt+1);
				map[i][j] = 'X';
			}
		}
	}
	
	static boolean findS(int r, int c) {
		
		
		for(int i=0; i<4; i++) {
			int nextR = r;
			int nextC = c;
			while(true) {
				nextR += moveR[i];
				nextC += moveC[i];
				if(nextR<0 || nextR>=N || nextC<0 || nextC>=N)
					break;
				if(map[nextR][nextC] == 'O')
					break;
				
				if(map[nextR][nextC] == 'S')
					return true;
			
			}
		}
		
		return false;
	}

}