import java.io.*;
import java.util.*;

/*
다시 풀 예정이요,,
 */

public class codetree_민트초코우유 {
    public static final int MAX_SIZE = 55;
    public static final int DIR_COUNT = 4;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static int gridSize, totalDays;
    public static String[] initialFoodGrid = new String[MAX_SIZE];
    public static int[][] foodGrid = new int[MAX_SIZE][MAX_SIZE];
    public static int[][] faithGrid = new int[MAX_SIZE][MAX_SIZE];

    public static boolean[][] visited = new boolean[MAX_SIZE][MAX_SIZE];
    public static boolean[][] defended = new boolean[MAX_SIZE][MAX_SIZE];

    public static ArrayList<LeaderEntry> leaderList = new ArrayList<>();
    public static ArrayList<GroupMember> groupMembers = new ArrayList<>();
    public static int currentGroupSize;

    public static class PairII {
        int first, second;
        public PairII(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class LeaderEntry {
        public PairII first;
        public PairII second;
        public LeaderEntry(PairII first, PairII second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class GroupMember {
        int faithNeg;
        public PairII pos; // {행 번호, 열 번호}
        public GroupMember(int faithNeg, PairII pos) {
            this.faithNeg = faithNeg;
            this.pos = pos;
        }
    }

    public static boolean isOutOfBounds(int row, int col) {
        return row < 1 || row > gridSize || col < 1 || col > gridSize;
    }

    public static int countBasicFoods(int foodValue) {
        int count = 0;
        if ((foodValue & 1) != 0) count++;
        if ((foodValue & 2) != 0) count++;
        if ((foodValue & 4) != 0) count++;
        return count;
    }

    public static void dfsGroup(int row, int col, int targetFood) {
        visited[row][col] = true;
        currentGroupSize++;
        groupMembers.add(new GroupMember(-faithGrid[row][col], new PairII(row, col)));

        for (int dir = 0; dir < DIR_COUNT; dir++) {
            int newRow = row + dx[dir];
            int newCol = col + dy[dir];
            if(isOutOfBounds(newRow, newCol)) continue;
            if(visited[newRow][newCol]) continue;
            if(foodGrid[newRow][newCol] != targetFood) continue;
            dfsGroup(newRow, newCol, targetFood);
        }
    }

    public static void performLunchPhase() {
        leaderList.clear();
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                if (visited[i][j]) continue;
                groupMembers.clear();
                currentGroupSize = 0;
                int currentFood = foodGrid[i][j];
                dfsGroup(i, j, currentFood);

                Collections.sort(groupMembers, new Comparator<GroupMember>() {
                    public int compare(GroupMember a, GroupMember b) {
                        if(a.faithNeg != b.faithNeg) return a.faithNeg - b.faithNeg;
                        if(a.pos.first != b.pos.first) return a.pos.first - b.pos.first;
                        return a.pos.second - b.pos.second;
                    }
                });
                int leaderRow = groupMembers.get(0).pos.first;
                int leaderCol = groupMembers.get(0).pos.second;

                faithGrid[leaderRow][leaderCol] += currentGroupSize;

                int basicFoodCount = countBasicFoods(foodGrid[i][j]);

                leaderList.add(new LeaderEntry(new PairII(basicFoodCount, -faithGrid[leaderRow][leaderCol]), new PairII(leaderRow, leaderCol)));
            }
        }
    }

    public static void performEveningPropagation() {
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                defended[i][j] = false;
            }
        }

        Collections.sort(leaderList, new Comparator<LeaderEntry>() {
            public int compare(LeaderEntry a, LeaderEntry b) {
                if(a.first.first != b.first.first) return a.first.first - b.first.first;
                if(a.first.second != b.first.second) return a.first.second - b.first.second;
                if(a.second.first != b.second.first) return a.second.first - b.second.first;
                return a.second.second - b.second.second;
            }
        });

        for (int idx = 0; idx < leaderList.size(); idx++) {
            int leaderRow = leaderList.get(idx).second.first;
            int leaderCol = leaderList.get(idx).second.second;
            if (defended[leaderRow][leaderCol]) continue;

            int propagationDir = faithGrid[leaderRow][leaderCol] % 4;
            int currentRow = leaderRow;
            int currentCol = leaderCol;

            int remainingPropagationPower = faithGrid[leaderRow][leaderCol] - 1;
            faithGrid[leaderRow][leaderCol] = 1;

            while (true) {
                currentRow += dx[propagationDir];
                currentCol += dy[propagationDir];
                if (isOutOfBounds(currentRow, currentCol)) break;
                if (foodGrid[currentRow][currentCol] == foodGrid[leaderRow][leaderCol])
                    continue;

                int targetFaith = faithGrid[currentRow][currentCol];
                if (remainingPropagationPower > targetFaith) {
                    faithGrid[currentRow][currentCol] += 1;
                    remainingPropagationPower -= (targetFaith + 1);
                    foodGrid[currentRow][currentCol] = foodGrid[leaderRow][leaderCol];
                    defended[currentRow][currentCol] = true;
                } else {
                    faithGrid[currentRow][currentCol] += remainingPropagationPower;
                    foodGrid[currentRow][currentCol] |= foodGrid[leaderRow][leaderCol];
                    remainingPropagationPower = 0;
                    defended[currentRow][currentCol] = true;
                }
                if (remainingPropagationPower == 0) break;
            }
        }
    }

    public static void computeAndPrintDailyAnswer() {
        int[] faithSums = new int[10];
        for (int i = 1; i <= gridSize; i++) {
            for (int j = 1; j <= gridSize; j++) {
                faithSums[ foodGrid[i][j] ] += faithGrid[i][j];
            }
        }
        System.out.println(faithSums[7] + " "
                + faithSums[3] + " "
                + faithSums[5] + " "
                + faithSums[6] + " "
                + faithSums[4] + " "
                + faithSums[2] + " "
                + faithSums[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        gridSize = Integer.parseInt(st.nextToken());
        totalDays = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= gridSize; i++) {
            initialFoodGrid[i] = br.readLine();
            initialFoodGrid[i] = " " + initialFoodGrid[i];
            for (int j = 1; j <= gridSize; j++) {
                char foodChar = initialFoodGrid[i].charAt(j);
                if (foodChar == 'T') {
                    foodGrid[i][j] = 1;
                } else if (foodChar == 'C') {
                    foodGrid[i][j] = 2;
                } else if (foodChar == 'M') {
                    foodGrid[i][j] = 4;
                }
            }
        }

        for (int i = 1; i <= gridSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= gridSize; j++) {
                faithGrid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int day = 1; day <= totalDays; day++) {
            performLunchPhase();
            performEveningPropagation();
            computeAndPrintDailyAnswer();
        }
    }
}
