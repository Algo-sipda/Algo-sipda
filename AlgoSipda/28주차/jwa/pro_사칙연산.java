class Solution {
    public int solution(String arr[]) {
        int n = arr.length;
        int[][] dpMin = new int[n][n];
        int[][] dpMax = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dpMin[i][j] = Integer.MAX_VALUE;
                dpMax[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 0; i < n; i += 2) {
            dpMin[i][i] = Integer.parseInt(arr[i]);
            dpMax[i][i] = Integer.parseInt(arr[i]);
        }

        for (int step = 2; step < n; step += 2) {
            for (int j = 0; j + step < n; j += 2) {
                for (int k = j + 1; k < j + step; k += 2) {
                    if (arr[k].equals("+")) {
                        dpMin[j][j + step] = Math.min(dpMin[j][j + step],
                                dpMin[j][k - 1] + dpMin[k + 1][j + step]);
                        dpMax[j][j + step] = Math.max(dpMax[j][j + step],
                                dpMax[j][k - 1] + dpMax[k + 1][j + step]);
                    } else {
                        dpMin[j][j + step] = Math.min(dpMin[j][j + step],
                                dpMin[j][k - 1] - dpMax[k + 1][j + step]);
                        dpMax[j][j + step] = Math.max(dpMax[j][j + step],
                                dpMax[j][k - 1] - dpMin[k + 1][j + step]);
                    }
                }
            }
        }

        return dpMax[0][n - 1];
    }
}
