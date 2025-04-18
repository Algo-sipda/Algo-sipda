import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static StringBuilder result;
    static boolean found;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        result = new StringBuilder();
        dfs("");
        System.out.println(result);
    }

    static void dfs(String s) {
        if (found) return;
        if (s.length() == n) {
            result.append(s);
            found = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (isValid(s + i)) dfs(s + i);
        }
    }

    static boolean isValid(String s) {
        int len = s.length();
        for (int i = 1; i <= len / 2; i++) {
            String a = s.substring(len - 2 * i, len - i);
            String b = s.substring(len - i);
            if (a.equals(b)) return false;
        }
        return true;
    }
} 