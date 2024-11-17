import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[][] conditions;
    static int[] students;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        conditions = new int[k][3];
        students = new int[n];

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            conditions[i][0] = Integer.parseInt(st.nextToken()) - 1;
            conditions[i][1] = Integer.parseInt(st.nextToken()) - 1;
            conditions[i][2] = c.equals("S") ? 1 : 0;
        }

        comb(0);

        System.out.println(result);
    }

    public static void comb(int idx) {
        if(idx == n) {
            for(int[] condition: conditions) {
                int a = condition[0];
                int b = condition[1];
                int c = condition[2];

                if(c == 1 && students[a] != students[b]) return;
                if(c == 0 && students[a] == students[b]) return;
            }
            result++;
            return;
        }

        for(int i = 0; i < 3; i++) {
            students[idx] = i;
            comb(idx + 1);
        }
    }
}