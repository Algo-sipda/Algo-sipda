import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_다이어트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        long left = 1;
        long right = 2;
        boolean flag = false;
        while (right < 100000) {
            long minus = (long) Math.pow(right, 2) - (long) Math.pow(left, 2);
            if (minus == G) {
                flag = true;
                System.out.println(right);
            }
            if (minus < G) {
                right++;
            } else {
                left++;
            }
        }
        if (!flag) {
            System.out.println(-1);
        }
    }
}
