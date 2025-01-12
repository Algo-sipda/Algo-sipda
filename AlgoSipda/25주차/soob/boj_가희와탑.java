import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 건물의 개수
        int a = Integer.parseInt(st.nextToken());   // 가희가 볼 수 있는 건물의 개수
        int b = Integer.parseInt(st.nextToken());   // 단비가 볼 수 있는 건물의 개수

        int maxH = Math.max(a, b);

        Deque<Integer> list = new ArrayDeque<>();
        list.add(maxH);

        for (int i = a - 1; i > 0; i--) {
            list.addFirst(i);
        }
        
        for (int i = 1; i < b; i++) {
            list.addLast(b - i);
        }

        if (list.size() > N)
            System.out.println(-1);
        else {
            int idx = 0;
            for (int n : list) {
                System.out.print(n + " ");
                if (idx == 0) {
                    for (int i = 0; i < N - list.size(); i++) {
                        System.out.print(1 + " ");
                    }
                }
                idx++;
            }
        }
    }
}