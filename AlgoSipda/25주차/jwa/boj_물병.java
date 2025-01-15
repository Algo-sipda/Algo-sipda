import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (Integer.bitCount(N) > K) {
            answer += N & (-N);
            N += N & (-N);
        }

        System.out.println(answer);
    }
}
