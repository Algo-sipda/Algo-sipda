import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] calendar = new int[367];

        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            for (int j = start; j <= end; j++) {
                calendar[j]++;
            }
        }

        int width = 0, height = 0, area = 0;

        for (int i = 1; i <= 366; i++) {
            if (calendar[i] > 0) {
                width++;
                height = Math.max(height, calendar[i]);
            } else if (width > 0) {
                area += width * height;
                width = 0;
                height = 0;
            }
        }

        area += width * height;

        System.out.println(area);
    }
}