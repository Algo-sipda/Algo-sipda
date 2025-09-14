import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_망가진키보드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int m = Integer.parseInt(br.readLine());
            if (m == 0) {
                break;
            }
            String sentence = br.readLine();
            int[] word = new int[128];
            int left = -1;
            int right = -1;
            int max = 0;
            int cnt = 0;
            while (left <= right) {
                if (cnt < m) {
                    right++;
                    word[sentence.charAt(right)]++;
                    if (word[sentence.charAt(right)] == 1) {
                        cnt++;
                    }
                } else if (cnt == m && word[sentence.charAt(right + 1)] > 0) {
                    right++;
                    word[sentence.charAt(right)]++;
                } else {
                    left++;
                    word[sentence.charAt(left)]--;
                    if (word[sentence.charAt(left)] == 0) {
                        cnt--;
                    }
                }
                max = Math.max(max, right - left);
                if (right == sentence.length() - 1) {
                    break;
                }
            }
            System.out.println(max);
        }
    }
}
