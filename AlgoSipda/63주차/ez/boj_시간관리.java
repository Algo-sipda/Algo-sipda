import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_시간관리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] work = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(work, (o1, o2) -> o2[1] - o1[1]);

        int answer = work[0][1] - work[0][0];

        for (int i = 1; i < n; i++) {
            if (work[i][1] < answer) {
                answer = work[i][1];
            }
            answer -= work[i][0];
        }

        if (answer > 0) {
            System.out.println(answer);
        } else  {
            System.out.println(-1);
        }
    }

}