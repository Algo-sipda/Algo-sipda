import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_전구와스위치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] bulbs = br.readLine().toCharArray();
        char[] firstBulbs = Arrays.copyOf(bulbs,bulbs.length);

        firstBulbs[0] = bulbs[0] == '1' ? '0' : '1';
        firstBulbs[1] = bulbs[1] == '1' ? '0' : '1';

        char[] target = br.readLine().toCharArray();

        int count = 0;
        int firstCnt = 1;

        for (int i = 1; i < bulbs.length; i++) {
            if (bulbs[i - 1] != target[i - 1]) {
                bulbs[i - 1] = bulbs[i - 1] == '1' ? '0' : '1';
                bulbs[i] = bulbs[i] == '1' ? '0' : '1';

                if (i + 1 < bulbs.length) {
                    bulbs[i + 1] = bulbs[i + 1] == '1' ? '0' : '1';
                }

                count++;
            }
            if (firstBulbs[i-1] != target[i-1]) {
                firstBulbs[i-1] = firstBulbs[i-1] == '1' ? '0' : '1';
                firstBulbs[i] = firstBulbs[i] == '1' ? '0' : '1';

                if (i + 1 < firstBulbs.length) {
                    firstBulbs[i+1] = firstBulbs[i+1] == '1' ? '0' : '1';
                }

                firstCnt++;
            }
        }

        if (isSame(bulbs,target)) {
            System.out.println(count);
        } else if (isSame(firstBulbs,target)) {
            System.out.println(firstCnt);
        } else {
            System.out.print(-1);
        }
    }

    public static boolean isSame(char[] a, char[] b){
        for (int i = 0; i < a.length; i++) {
            if (b[i] != a[i]) {
                return false;
            }
        }

        return true;
    }

}
