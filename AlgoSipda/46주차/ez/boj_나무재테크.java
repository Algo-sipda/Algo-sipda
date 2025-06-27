import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_나무재테크 {

    static int N, M, K;
    static int[][] map, copy;
    static List<Tree> trees;
    static Queue<Integer> deadTrees;

    static class Tree {
        int r, c, o;
        boolean isDead;
        public Tree(int r, int c, int o, boolean isDead) {
            this.r = r;
            this.c = c;
            this.o = o;
            this.isDead = isDead;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        copy = new int[N + 1][N + 1];
        trees = new ArrayList<>();
        deadTrees = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                copy[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, o, false));
        }

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    private static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if (tree.o <= map[tree.r][tree.c]) {
                map[tree.r][tree.c] -= tree.o;
                tree.o += 1;
            } else {
                deadTrees.add(i);
            }
        }
    }

    private static void summer() {
        while (!deadTrees.isEmpty()) {
            Tree tree = trees.get(deadTrees.poll());
            map[tree.r][tree.c] += (tree.o / 2);
            tree.isDead = true;
        }
    }

    private static void fall() {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<Tree> newTrees = new ArrayList<>();
        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).o % 5 != 0 || trees.get(i).isDead) continue;
            for (int d = 0; d < 8; d++) {
                int nr = trees.get(i).r + dr[d];
                int nc = trees.get(i).c + dc[d];
                if (isOut(nr, nc)) continue;
                newTrees.add(new Tree(nr, nc, 1, false));
            }
        }

        for (Tree tree : trees) {
            if (tree.isDead) continue;
            newTrees.add(tree);
        }
        trees = newTrees;
    }

    private static void winter() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                map[i][j] += copy[i][j];
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 1 || nc < 1 || nr > N || nc > N;
    }
}