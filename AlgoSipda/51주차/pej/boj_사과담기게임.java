import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// [BOJ] 사과 담기 게임
// https://www.acmicpc.net/problem/2828
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int movecnt = 0;
        int startIdx = 1; // 1부터 시작한다고 명시되어 있음 
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(br.readLine()); // 떨어지는 사과의 개수
        for(int i = 0; i < J; i++){
            int idx = Integer.parseInt(br.readLine());
            if(isIn(idx, startIdx, M)){ // 내부에 있다면, 움직일 필요없음
                continue;
            }
            // 가장 적게 움직이는 것을 선택 
            if(idx < startIdx) { // 왼쪽 끝을 idx로 맞추기 
                movecnt += startIdx - idx;
                startIdx = idx;
            } else { // 오른쪽 끝을 idx로 맞추면 됨, 즉, 왼쪽 끝은 idx - M + 1
                movecnt += idx - ( startIdx  + M - 1 );
                startIdx = idx -M + 1;
            }
        }
        System.out.println(movecnt);
    }
    static boolean isIn(int idx, int sIdx, int size){
        return idx >= sIdx && idx <= sIdx + size - 1;
    }
}
