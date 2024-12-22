import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[K][2];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int answer = 0;
        int cur = 0;
        for(int i = 0; i < K; i++) {
            if(arr[i][0] < cur) {
                continue;
            }else {
                cur = arr[i][1];
                answer++;
            }
        }
        if(cur <= N) {
            answer++;
        }

        System.out.println(answer);
    }
}