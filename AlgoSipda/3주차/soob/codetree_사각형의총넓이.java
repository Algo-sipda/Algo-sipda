import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Rect> rectList = new ArrayList<>();
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            rectList.add(new Rect(lx, ry, rx, ly));
            xList.add(lx);
            xList.add(rx);
            yList.add(ly);
            yList.add(ry);
        }

        Collections.sort(xList);
        Collections.sort(yList);

        int totalArea = 0;

        for (int i = 0; i < xList.size() - 1; i++) {
            for (int j = 0; j < yList.size() - 1; j++) {
                for (int k = 0; k < rectList.size(); k++) {
                    if (xList.get(i) >= rectList.get(k).leftX && xList.get(i + 1) <= rectList.get(k).rightX &&
                            yList.get(j) >= rectList.get(k).leftY && yList.get(j + 1) <= rectList.get(k).rightY) {
                        totalArea += (xList.get(i + 1) - xList.get(i)) * (yList.get(j + 1) - yList.get(j));
                        break;
                    }
                }
            }
        }

        System.out.println(totalArea);
    }

    public static class Rect {
        int leftX;
        int leftY;
        int rightX;
        int rightY;

        public Rect(int leftX, int leftY, int rightX, int rightY) {
            this.leftX = leftX;
            this.leftY = leftY;
            this.rightX = rightX;
            this.rightY = rightY;
        }
    }
}