import java.util.Scanner;

public class boj_LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstWord = sc.next();
        String secondWord = sc.next();
        int firstLen = firstWord.length();
        int secondLen = secondWord.length();

        int[][] lcs = new int[firstLen + 1][secondLen + 1];

        for (int i = 1; i <= firstLen; i++) {
            for (int j = 1; j <= secondLen; j++) {
                if (firstWord.charAt(i - 1) == secondWord.charAt(j - 1))
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        int max = 0;
        for (int i = 0; i <= firstLen; i++) {
            for (int j = 0; j <= secondLen; j++) {
                max = Math.max(max, lcs[i][j]);
            }
        }

        System.out.println(max);

    }
}