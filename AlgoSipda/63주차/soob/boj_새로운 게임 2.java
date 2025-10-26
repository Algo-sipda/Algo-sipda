import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int[][] color;
    static ArrayList<Integer>[][] cell;
    static int[] x;
    static int[] y;
    static int[] d;
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  // 체스판 크기
        k = Integer.parseInt(st.nextToken());  // 말 개수

        color = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cell = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cell[i][j] = new ArrayList<>();
            }
        }

        x = new int[k];
        y = new int[k];
        d = new int[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int xi = Integer.parseInt(st.nextToken()) - 1;
            int yi = Integer.parseInt(st.nextToken()) - 1;
            int di = Integer.parseInt(st.nextToken());
            x[i] = xi;
            y[i] = yi;
            d[i] = di;
            cell[xi][yi].add(i);
        }

        int turn = 0;
        int ans = -1;
        while (turn <= 1000) {
            turn++;
            for (int i = 0; i < k; i++) {
                if (move(i)) {
                    ans = turn;
                    System.out.println(ans);
                    return;
                }
            }
            if (turn == 1000) break;
        }
        System.out.println(-1);
    }

    static boolean move(int idx) {
        int cx = x[idx];
        int cy = y[idx];
        int dir = d[idx];
        int nx = cx + dx[dir];
        int ny = cy + dy[dir];

        if (!in(nx, ny) || color[nx][ny] == 2) {
            d[idx] = rev(dir);
            dir = d[idx];
            nx = cx + dx[dir];
            ny = cy + dy[dir];
            if (!in(nx, ny) || color[nx][ny] == 2)
                return false;
        }

        int pos = 0;
        for (int i = 0; i < cell[cx][cy].size(); i++) {
            if (cell[cx][cy].get(i) == idx) {
                pos = i;
                break;
            }
        }

        List<Integer> moving = new ArrayList<>();
        for (int i = pos; i < cell[cx][cy].size(); i++) {
            moving.add(cell[cx][cy].get(i));
        }
        int sz = cell[cx][cy].size();
        for (int i = sz - 1; i >= pos; i--) {
            cell[cx][cy].remove(i);
        }

        if (color[nx][ny] == 1)
            Collections.reverse(moving);

        for (int id : moving) {
            cell[nx][ny].add(id);
            x[id] = nx;
            y[id] = ny;
        }

        return cell[nx][ny].size() >= 4;
    }

    static boolean in(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    static int rev(int dir) {
        if (dir == 1) return 2;
        if (dir == 2) return 1;
        if (dir == 3) return 4;
        return 3;
    }
}
