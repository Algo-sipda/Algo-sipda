/*
다시 풀기
 */

class pro_표현가능한이진트리 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i<numbers.length; i++) {
            String binaryTree = getBinaryTree(numbers[i]);
            if (checkTree(binaryTree)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    private String getBinaryTree(long number) {
        String binary = Long.toBinaryString(number);
        int height = (int) Math.ceil(Math.log10(binary.length() + 1) / Math.log10(2));
        int size = (int) Math.pow(2, height) - 1;
        int dummy = size - binary.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dummy; i++) {
            sb.append("0");
        }

        sb.append(binary);
        return sb.toString();
    }

    private boolean checkTree(String binaryTree) {
        if (binaryTree.length() <= 1) {
            return true;
        }

        String leftTree = binaryTree.substring(0, binaryTree.length() / 2);
        String rightTree = binaryTree.substring(binaryTree.length() / 2 + 1);

        char root = binaryTree.charAt(binaryTree.length() / 2);
        char left = leftTree.charAt(leftTree.length() / 2);
        char right = rightTree.charAt(rightTree.length() / 2);

        if (root == '0' && (left == '1' || right == '1'))
            return false;

        return checkTree(leftTree) && checkTree(rightTree);
    }
}