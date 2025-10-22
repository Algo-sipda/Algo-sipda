import java.util.*;

public class Main {
	
	public static int[] arr;
	public static int N;
	public static int cnt=0;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N];
		
		nQueen(0);
		
		System.out.println(cnt);
	}
	
	public static void nQueen(int depth) {
		if(depth==N) {//모든 원소를 다 채운 상태면 cnt 증가
			cnt++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			arr[depth]=i;
			if(Possibility(depth)) {//놓을 수 있는 위치일 경우 재귀함수 호출
				nQueen(depth+1);
			}
		}
	}
	
	//놓을 위치가 다른 퀸으로부터 위협받는지를 검사하는 조건문
	public static boolean Possibility(int col) {
		for(int i=0;i<col;i++) {
			if(arr[col]==arr[i])//해당 열의 행과 i열의 행이 일치할 경우(같은 행에 존재할 경우)
				return false;
			else if(Math.abs(col-i)==Math.abs(arr[col]-arr[i])) {//열의차와 행의 차가 같은 경우 대각선에 놓여있는 경우
				//대각선상에 놓여있는 경우
				return false;
			}
		}
		return true;
	}
}