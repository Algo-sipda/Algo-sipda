import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());    // 참가한 사람의 수
        int n = Integer.parseInt(br.readLine());    // 가로 막대가 놓일 전체 가로 줄의 수
        char[] resultOrder = br.readLine().toCharArray();   // 사다리를 타고 난 후 결정된 참가자들의 최종 순서
        char[] order = new char[k]; // 참가자들의 순서
        for (int i = 0; i < k; i++) {
            order[i] = (char) (65 + i);
        }
        char[] preOrder = Arrays.copyOf(order, k);  // 감추어진 가로줄 이전 참가자 순서
        char[] nextOrder = Arrays.copyOf(resultOrder, k); // 감추어진 가로줄 이후 참가자 순서
        char[][] ladder = new char[n][k];

        for (int i = 0; i < n; i++) {
            ladder[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            if (ladder[i][0] == '?')
                break;
            for (int j = 0; j < k - 1; j++) {
                char l = ladder[i][j];
                if (l == '-') {
                    char c = preOrder[j];
                    preOrder[j] = preOrder[j + 1];
                    preOrder[j + 1] = c;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (ladder[i][0] == '?')
                break;
            for (int j = 0; j < k - 1; j++) {
                char l = ladder[i][j];
                if (l == '-') {
                    char c = nextOrder[j];
                    nextOrder[j] = nextOrder[j + 1];
                    nextOrder[j + 1] = c;
                }
            }
        }

        String answer = "";
        boolean pre = false;
        for (int i = 0; i < k - 1; i++) {
            if (preOrder[i] != nextOrder[i]) {
                if (pre) {
                    answer = "x".repeat(k - 1);
                    break;
                }
                if (preOrder[i] != nextOrder[i + 1] || preOrder[i + 1] != nextOrder[i]) {
                    answer = "x".repeat(k - 1);
                    break;
                }
                answer += "-";
                preOrder[i] = nextOrder[i];
                preOrder[i + 1] = nextOrder[i + 1];
                pre = true;
            } else {
                answer += "*";
                pre = false;
            }
        }

        System.out.println(answer);
    }
}
