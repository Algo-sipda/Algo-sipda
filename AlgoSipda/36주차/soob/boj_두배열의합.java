import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) B[i] = Integer.parseInt(st.nextToken());

        List<Integer> sumA = new ArrayList<>();
        List<Integer> sumB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB);

        int x = 0, y = sumB.size() - 1;
        long result = 0;

        while (x < sumA.size() && y >= 0) {
            int a = sumA.get(x);
            int b = sumB.get(y);
            int sum = a + b;
            if (sum == T) {
                long countA = 0, countB = 0;
                while (x < sumA.size() && sumA.get(x) == a) {
                    countA++;
                    x++;
                }
                while (y >= 0 && sumB.get(y) == b) {
                    countB++;
                    y--;
                }
                result += countA * countB;
            } else if (sum < T) {
                x++;
            } else {
                y--;
            }
        }

        System.out.println(result);
    }
}
