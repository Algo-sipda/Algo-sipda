import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_두배열의합 {

    static long T;
    static int N, M;
    static long[] A, B;
    static List<Long> subList1, subList2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        subList1 = new ArrayList<>();
        subList2 = new ArrayList<>();

        T = Long.parseLong(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            subList1.add(A[i]);
        }
        M = Integer.parseInt(br.readLine());
        B = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            subList2.add(B[i]);
        }

        getSum(A, subList1);
        getSum(B, subList2);

        long res = 0;
        int idxA = 0;
        int idxB = subList2.size() - 1;
        while (true) {
            long curA = subList1.get(idxA);
            long left = T - curA;
            long curB = subList2.get(idxB);
            if (curB == left) {
                long cntA = 0;
                long cntB = 0;
                while (idxA < subList1.size() && subList1.get(idxA) == curA) {
                    cntA++;
                    idxA++;
                }
                while (idxB >= 0 && subList2.get(idxB) == left) {
                    cntB++;
                    idxB--;
                }
                res += cntA * cntB;
            } else if (curB > left) {
                idxB--;
            } else {
                idxA++;
            }

            if (idxA == subList1.size() || idxB == -1) {
                break;
            }
        }

        System.out.println(res);
    }

    private static void getSum(long[] arr, List<Long> list) {
        for (int i = 0; i < arr.length; i++) {
            long sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }

        Collections.sort(list);
    }
}
