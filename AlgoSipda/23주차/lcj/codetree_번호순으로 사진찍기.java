import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[K][2];
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int answer = 0;
        int last = 0;
        for(int[] a: arr) {
            if(a[0] < last) continue;
            else {
                last = a[1];
                answer++;
            }
        }

        if(last <= N) answer++;
        System.out.println(answer);
    }
}