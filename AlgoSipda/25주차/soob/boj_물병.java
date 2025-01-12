import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;

        while (Integer.bitCount(N) > K) {
            // 가장 오른쪽의 1을 찾음
            int smallestBottle = N & -N;
            answer += smallestBottle;
            N += smallestBottle; // 물병 추가
        }

        System.out.println(answer);
    }
}