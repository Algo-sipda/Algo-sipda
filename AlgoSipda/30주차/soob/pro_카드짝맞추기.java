import java.util.*;

class Solution {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] board, int r, int c) {
        Map<Integer, List<int[]>> cardMap = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    cardMap.putIfAbsent(board[i][j], new ArrayList<>());
                    cardMap.get(board[i][j]).add(new int[]{i, j});
                }
            }
        }

        List<Integer> cardList = new ArrayList<>(cardMap.keySet());
        return findMinMoves(board, cardList, r, c, cardMap);
    }

    private int findMinMoves(int[][] board, List<Integer> cardList, int r, int c, Map<Integer, List<int[]>> cardMap) {
        int minMoves = Integer.MAX_VALUE;
        List<List<Integer>> permutations = new ArrayList<>();
        generatePermutations(cardList, 0, permutations);

        for (List<Integer> order : permutations) {
            int[][] tempBoard = new int[4][4];
            for (int i = 0; i < 4; i++) {
                tempBoard[i] = board[i].clone();
            }

            int cr = r, cc = c, moves = 0;
            for (int card : order) {
                List<int[]> pos = cardMap.get(card);
                int[] first = pos.get(0), second = pos.get(1);

                int d1 = bfs(cr, cc, first[0], first[1], tempBoard);
                int d2 = bfs(first[0], first[1], second[0], second[1], tempBoard);
                int d3 = bfs(cr, cc, second[0], second[1], tempBoard);
                int d4 = bfs(second[0], second[1], first[0], first[1], tempBoard);

                if (d1 + d2 < d3 + d4) {
                    moves += d1 + d2 + 2;
                    cr = second[0];
                    cc = second[1];
                } else {
                    moves += d3 + d4 + 2;
                    cr = first[0];
                    cc = first[1];
                }

                tempBoard[first[0]][first[1]] = 0;
                tempBoard[second[0]][second[1]] = 0;
            }
            minMoves = Math.min(minMoves, moves);
        }
        return minMoves;
    }

    private void generatePermutations(List<Integer> cardList, int depth, List<List<Integer>> permutations) {
        if (depth == cardList.size()) {
            permutations.add(new ArrayList<>(cardList));
            return;
        }
        for (int i = depth; i < cardList.size(); i++) {
            Collections.swap(cardList, depth, i);
            generatePermutations(cardList, depth + 1, permutations);
            Collections.swap(cardList, depth, i);
        }
    }

    private int bfs(int sr, int sc, int er, int ec, int[][] board) {
        if (sr == er && sc == ec) return 0;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], moves = curr[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (isValid(nx, ny, visited)) {
                    if (nx == er && ny == ec)
                        return moves + 1;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, moves + 1});
                }

                int[] ctrlMove = ctrlMove(x, y, d, board);
                if (!visited[ctrlMove[0]][ctrlMove[1]]) {
                    if (ctrlMove[0] == er && ctrlMove[1] == ec)
                        return moves + 1;
                    visited[ctrlMove[0]][ctrlMove[1]] = true;
                    queue.offer(new int[]{ctrlMove[0], ctrlMove[1], moves + 1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean isValid(int x, int y, boolean[][] visited) {
        return x >= 0 && x < 4 && y >= 0 && y < 4 && !visited[x][y];
    }

    private int[] ctrlMove(int x, int y, int d, int[][] board) {
        while (true) {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                return new int[]{x, y};
            if (board[nx][ny] > 0)
                return new int[]{nx, ny};
            x = nx;
            y = ny;
        }
    }
}
