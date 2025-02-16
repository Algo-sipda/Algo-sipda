class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = toBinaryString(numbers[i]);
            answer[i] = isValidBinaryTree(binary) ? 1 : 0;
        }
        
        return answer;
    }
    
    private String toBinaryString(long num) {
        String binary = Long.toBinaryString(num);
        int len = binary.length();
        int fullLength = 1;
        
        while (fullLength - 1 < len) {
            fullLength = fullLength * 2;
        }
        
        int padding = fullLength - 1 - len;
        return "0".repeat(padding) + binary;
    }
    
    private boolean isValidBinaryTree(String binary) {
        return checkTree(binary, 0, binary.length() - 1);
    }
    
    private boolean checkTree(String binary, int start, int end) {
        if (start > end) return true;
        
        int mid = (start + end) / 2;
        
        if (binary.charAt(mid) == '0') {
            for (int i = start; i <= end; i++) {
                if (binary.charAt(i) == '1') return false;
            }
        }
        
        return checkTree(binary, start, mid - 1) && checkTree(binary, mid + 1, end);
    }
}
