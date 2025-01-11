import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_가희와탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b - 1 > N) {
            System.out.println(-1);
            return;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < a; i++) {
            list.add(i);
        }
        list.add(Math.max(a, b));
        for (int i = b - 1; i > 0; i--) {
            list.add(i);
        }

        if (a == 1) {
            while (list.size() < N) {
                list.add(1, 1);
            }
        } else {
            while (list.size() < N) {
                list.add(0, 1);
            }
        }

        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}