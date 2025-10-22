import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_NQueen {
    static int[] chess;
    static boolean[] check; //가로
    static boolean[] check_left; //왼쪽 대각선
    static boolean[] check_right; //오른쪽 대각선
    static int count=0; //출력
    static int n=0; //입력
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());

        chess=new int[n];
        check=new boolean[n];
        check_left=new boolean[2 * n - 1];
        check_right=new boolean[2 * n - 1];

        queen(0);

        System.out.println(count);
    }

    public static void queen(int x){
        if(x == n){
            count++;
            return;
        }
        for (int j = 0; j < n; j++) {//행
            if (check[j] == false && check_left[x + j] == false && check_right[x - j + n - 1] == false) { //가로줄, 왼쪽 대각선, 오른쪽 대각선에 퀸이 놓여있지 않다면
                chess[x] = j;//퀸을 그 자리에 놓기


                check[j] = true; //가로선 왼오 대각선 true로 만들어 놓지 못하게 하기
                check_left[x + j] = true;
                check_right[x - j + n - 1] = true;

                queen(x + 1); // 재귀

                //재귀가 끝난 후
                //boolean 배열 모두 초기화해서 다음 경우의 수를 시도할 수 있도록
                check[j]=false;
                check_left[x + j]=false;
                check_right[x - j + n - 1]=false;
            }
        }
    }
}
