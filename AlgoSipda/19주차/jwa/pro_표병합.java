import java.util.*;

class Solution {
    static int[] parent = new int[2500];

    public static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y)
            parent[x] = y;
        else
            parent[y] = x;
    }

    public String[] solution(String[] commands) {
        String[][] table = new String[50][50];
        final String EMPTY = "EMPTY";
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < 50; i++) {
            Arrays.fill(table[i], EMPTY);
        }

        for (String next : commands) {
            StringTokenizer st = new StringTokenizer(next, " ");
            String command = st.nextToken();

            if (command.equals("UPDATE")) {
                if (st.countTokens() == 2) { // "UPDATE value1 value2"
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 50; j++) {
                            if (table[i][j].equals(value1))
                                table[i][j] = value2;
                        }
                    }
                } else { // "UPDATE r c value"
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken()) - 1;
                    String value = st.nextToken();
                    int root = find(r * 50 + c);
                    for (int i = 0; i < 2500; i++) {
                        if (parent[i] == root) {
                            int x = i / 50;
                            int y = i % 50;
                            table[x][y] = value;
                        }
                    }
                }
            } else if (command.equals("MERGE")) { // "MERGE r1 c1 r2 c2"
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;

                int root1 = find(r1 * 50 + c1);
                int root2 = find(r2 * 50 + c2);
                union(root1, root2);
                int root = find(root1);

                String value = EMPTY;
                if (table[r1][c1].equals(EMPTY) && !table[r2][c2].equals(EMPTY)) {
                    value = table[r2][c2];
                } else {
                    value = table[r1][c1];
                }

                for (int i = 0; i < 2500; i++) {
                    if (parent[i] == root1 || parent[i] == root2 || parent[i] == root) {
                        parent[i] = root;
                        int x = i / 50;
                        int y = i % 50;
                        table[x][y] = value;
                    }
                }
            } else if (command.equals("UNMERGE")) { // "UNMERGE r c"
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                String value = table[r][c];
                int root = find(r * 50 + c);
                for (int i = 0; i < 2500; i++) {
                    if (parent[i] == root) {
                        parent[i] = i;
                        int x = i / 50;
                        int y = i % 50;
                        table[x][y] = EMPTY;
                    }
                }
                table[r][c] = value;
            } else if (command.equals("PRINT")) { // "PRINT r c"
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                result.add(table[r][c]);
            }
        }

        return result.toArray(new String[0]);
    }
}
