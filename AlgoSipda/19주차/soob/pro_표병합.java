import java.util.*;

public class Solution {
    class Cell {
        String value;
        int rootRow, rootCol;

        Cell(int row, int col, String value) {
            this.rootRow = row;
            this.rootCol = col;
            this.value = value;
        }
    }

    private Cell[][] board;

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        board = new Cell[51][51];

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                board[i][j] = new Cell(i, j, "");
            }
        }

        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String cmdType = st.nextToken();

            switch (cmdType) {
                case "UPDATE":
                    if (st.countTokens() == 3) {
                        int row = Integer.parseInt(st.nextToken());
                        int col = Integer.parseInt(st.nextToken());
                        String value = st.nextToken();
                        updateCell(row, col, value);
                    } else {
                        String oldValue = st.nextToken();
                        String newValue = st.nextToken();
                        updateAllCells(oldValue, newValue);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());
                    mergeCells(r1, c1, r2, c2);
                    break;
                case "UNMERGE":
                    int row = Integer.parseInt(st.nextToken());
                    int col = Integer.parseInt(st.nextToken());
                    unmergeCell(row, col);
                    break;
                case "PRINT":
                    int pr = Integer.parseInt(st.nextToken());
                    int pc = Integer.parseInt(st.nextToken());
                    answer.add(printCell(pr, pc));
                    break;
            }
        }

        return answer.toArray(new String[0]);
    }

    private void updateCell(int row, int col, String value) {
        Cell root = findRoot(row, col);
        root.value = value;
    }

    private void updateAllCells(String oldValue, String newValue) {
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                Cell root = findRoot(i, j);
                if (root.value.equals(oldValue)) {
                    root.value = newValue;
                }
            }
        }
    }

    private void mergeCells(int r1, int c1, int r2, int c2) {
        Cell root1 = findRoot(r1, c1);
        Cell root2 = findRoot(r2, c2);

        if (root1 == root2) return;

        String mergedValue = root1.value.isEmpty() ? root2.value : root1.value;

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                Cell root = findRoot(i, j);
                if (root == root2) {
                    root.rootRow = root1.rootRow;
                    root.rootCol = root1.rootCol;
                }
            }
        }

        root1.value = mergedValue;
    }

    private void unmergeCell(int row, int col) {
        Cell root = findRoot(row, col);
        String currentValue = root.value;

        List<int[]> mergedCells = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                Cell cell = findRoot(i, j);
                if (cell == root) {
                    mergedCells.add(new int[]{i, j});
                }
            }
        }

        for (int[] cell : mergedCells) {
            int r = cell[0];
            int c = cell[1];
            board[r][c] = new Cell(r, c, "");
        }

        board[row][col].value = currentValue;
    }

    private String printCell(int row, int col) {
        Cell root = findRoot(row, col);
        return root.value.isEmpty() ? "EMPTY" : root.value;
    }

    private Cell findRoot(int row, int col) {
        Cell cell = board[row][col];
        if (cell.rootRow == row && cell.rootCol == col) {
            return cell;
        }
        return findRoot(cell.rootRow, cell.rootCol);
    }
}