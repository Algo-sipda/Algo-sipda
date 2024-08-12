import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] movingwalks;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // n: 무빙 워크의 길이
        k = Integer.parseInt(st.nextToken());   // k: 실험 종료 안정성 0인 판의 개수

        movingwalks = new int[2*n];
        visit = new boolean[2*n];
        st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<2*n;i++) {
            movingwalks[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int answer = 0;
        while(true) {
            answer++;

            // 1. 한칸 회전
            start = (start+2*n-1) % (2*n); // 시작점
            int exit = (start+n-1) % (2*n); // 절반 탈출구

            // 2. 회전 맨 앞에 부터 이동하기

            // 절반 탈출구에서 내릴 수 있는지 확인 (2n번째 칸은 1번째 칸의 위치로 이동 -> n번째 출구만 확인)
            if(visit[exit]) {
                visit[exit] = false;
            }

            int add = n*2;
            while(add > 0) {
                int cur = (start + add) % (2*n);
                int next = (cur + 1) % (2*n);

                // 앞에 사람이 있다 or 안전성 = 0 => 이동 X
                // 앞에 사람이 없다 and 안전성 > 0 => 이동 O
                if(visit[cur] && !visit[next] && movingwalks[next] > 0) {
                    visit[cur] = false;
                    visit[next] = true;
                    movingwalks[next]--;
                }

                add--;
            }

            if(visit[exit]) { // 앞으로 이동하고 나서 나갈 애들 내보내기
                visit[exit] = false;
            }

            // 3. 맨 앞 칸에 사람이 x + 안전성 = 0 => 사람 세우기
            if(!visit[start] && movingwalks[start] > 0) {
                visit[start] = true;
                movingwalks[start]--;
            }
            
            // 4. 안전성이 0인 칸이 k개 이상 -> 종료
            int cnt = 0;
            for(int i=0;i<movingwalks.length;i++) {
                if(movingwalks[i] == 0) cnt++;
            }
            if(cnt >= k) break;
        }
        System.out.println(answer);
    }
}