import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class boj_좋은수열 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        recur(new StringBuilder());
    }

    private static boolean recur(StringBuilder sb) {
        if (sb.toString().length() == N) {
            System.out.println(sb.toString());
            return true;
        }

        for (int i = 1; i < 4; i++) {
            if (isGood(sb.toString() + i)) {
                sb.append(i);
                if (recur(sb)) {
                    return true;
                }
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        return false;
    }

    private static boolean isGood(String str) {
        for (int i = 1; i <= str.length() / 2; i++) {
            String temp1 = str.substring(str.length() - i * 2, str.length() - i);
            String temp2 = str.substring(str.length() - i, str.length());
            if (temp1.equals(temp2)) {
                return false;
            }
        }
        return true;
    }
}