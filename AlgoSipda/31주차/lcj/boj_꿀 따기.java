import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;
	private static int[] honeys;
	private static long[] leftToRightTotal, rightToLeftTotal;
	private static long honeysTotal;
	private static long max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		honeys = new int[N];
		rightToLeftTotal = new long[N];
		leftToRightTotal = new long[N];
		
		st = new StringTokenizer(br.readLine()," ");
		long temp = 0;
		for(int i=0;i<N;i++) {
			honeys[i] = Integer.parseInt(st.nextToken());
			temp += honeys[i];
			leftToRightTotal[i] = temp;
		}
		
		temp = 0;
		for(int i=N-1;i>=0;i--) {
			temp += honeys[i];
			rightToLeftTotal[i] = temp;
		}
		honeysTotal = leftToRightTotal[N-1];
		haveHoneys();
		System.out.println(max);
	}
	
	private static void haveHoneys() {
		leftFix();
		rightFix();
		sideFix();
	}
	
	// 1번 케이스 - 벌통 왼쪽 고정
	private static void leftFix() {
		long bee1, bee2;
		for(int i=1;i<N-1;i++) {
			bee1 = honeysTotal - honeys[0] - honeys[i];
			bee2 = honeysTotal - leftToRightTotal[i];
			max = Math.max(max, bee1 + bee2);
		}
	}
	
	// 2번 케이스 - 벌통 오른쪽 고정
	private static void rightFix() {
		long bee1, bee2;
		
		for(int i=N-2;i>0;i--) {
			bee1 = honeysTotal - honeys[N-1] - honeys[i];
			bee2 = honeysTotal - rightToLeftTotal[i];
			max = Math.max(max, bee1 + bee2);
		}
	}
	
	
	// 3번 케이스 - 벌 양 끝 고정, 벌통 돌아다니기
	private static void sideFix() {
		long bee1, bee2;
		
		for(int i=1;i<N-1;i++) {
			bee1 = leftToRightTotal[i] - honeys[0];
			bee2 = rightToLeftTotal[i] - honeys[N-1];
			max = Math.max(max, bee1 + bee2);
		}
	}
}