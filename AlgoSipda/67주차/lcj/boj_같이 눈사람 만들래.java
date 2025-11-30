import java.io.*;
import java.util.*;

public class Main {
    static int N;  // 눈의 개수
    static int snow[];  // 눈의 높이를 저장하는 배열
    static int min = Integer.MAX_VALUE;  // 최소 높이 차이를 저장하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        snow = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int snowMan1 = snow[i] + snow[j];
                int start = 0;
                int end = N - 1;

                while (start < end) {
                    if (start == i || start == j) {
                        start++;
                        continue;
                    }
                    if (end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowMan2 = snow[start] + snow[end];
                    min = Math.min(min, Math.abs(snowMan1 - snowMan2));

                    if (snowMan1 > snowMan2)
                        start++;
                    else if (snowMan1 < snowMan2)
                        end--;
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }
        System.out.println(min);
    }
}