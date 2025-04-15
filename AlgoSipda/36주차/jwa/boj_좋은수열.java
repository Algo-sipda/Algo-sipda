import java.io.*;

public class Main {
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        backtracking("");
    }

    static void backtracking(String sequence) {
        if (sequence.length() == n) {
            System.out.println(sequence);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (check(sequence + i)) {
                backtracking(sequence + i);
            }
        }
    }

    static boolean check(String sequence) {
        int length = sequence.length() / 2;

        for (int i = 1; i <= length; i++) {
            if (sequence.substring(sequence.length() - i)
                    .equals(sequence.substring(sequence.length() - 2 * i, sequence.length() - i))) {
                return false;
            }
        }

        return true;
    }
}
