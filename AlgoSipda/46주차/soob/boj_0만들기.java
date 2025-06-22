import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            result.clear();
            dfs(1, "1");
            Collections.sort(result);
            for (String s : result) System.out.println(s);
            System.out.println();
        }
    }

    static void dfs(int num, String expr) {
        if (num == N) {
            if (eval(expr.replaceAll(" ", "")) == 0)
                result.add(expr);
            return;
        }

        int next = num + 1;
        dfs(next, expr + " " + next);
        dfs(next, expr + "+" + next);
        dfs(next, expr + "-" + next);
    }

    static int eval(String s) {
        int sum = 0, cur = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (j < s.length() && Character.isDigit(s.charAt(j))) j++;
            int n = Integer.parseInt(s.substring(i, j));
            if (op == '+') sum += n;
            if (op == '-') sum -= n;
            if (j < s.length()) op = s.charAt(j);
            i = j;
        }
        return sum;
    }
}
