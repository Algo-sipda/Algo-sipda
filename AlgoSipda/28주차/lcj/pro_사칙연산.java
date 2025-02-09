import java.util.*;

class Solution {
    
    int[][] min, max;
    int[] list;
    
    public int solution(String arr[]) {
        int size = arr.length/2+1;
        
        min = new int[size][size];
        max = new int[size][size];
        
        list = new int[size];
        
        list[0] = Integer.parseInt(arr[0]);
        for (int i = 2; i < arr.length; i+=2) {
            int num = Integer.parseInt(arr[i]);
            
            list[i/2] = arr[i-1].equals("+") ? num : -num;
        }
        
        for (int i = size-1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    min[i][j] = list[i];
                    max[i][j] = list[i];
                } else {
                    min[i][j] = Integer.MAX_VALUE;
                    max[i][j] = Integer.MIN_VALUE;
                    
                    for (int k = i; k < j; k++) {
                        boolean value = ((k == i) ? true : false); 
                        calculate(min[i][k], min[k+1][j], i, j, value);
                        calculate(min[i][k], max[k+1][j], i, j, value);
                        calculate(max[i][k], min[k+1][j], i, j, value);
                        calculate(max[i][k], max[k+1][j], i, j, value);
                    }
                }
            }
        }
        
        return max[0][size-1];
    }
    
    public void calculate(int num1, int num2, int r, int c, boolean value) {
        if (value && num1 < 0) {
            min[r][c] = Math.min(min[r][c], Math.min(num1-num2, num1+num2));
            max[r][c] = Math.max(max[r][c], Math.max(num1-num2, num1+num2));
        } else {
            min[r][c] = Math.min(min[r][c], num1+num2);
            max[r][c] = Math.max(max[r][c], num1+num2);
        }           
    }
}