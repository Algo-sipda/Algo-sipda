class pro_쿠키구입 {
    public int solution(int[] cookie) {
        int answer = 0;

        for (int mid = 1; mid < cookie.length; mid++) {
            int left = mid - 1;
            int right = mid;
            int leftSum = cookie[left];
            int rightSum = cookie[right];
            while (true) {
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }


                if (leftSum <= rightSum && left > 0) {
                    left--;
                    leftSum += cookie[left];
                } else if (leftSum > rightSum && right < cookie.length - 1) {
                    right++;
                    rightSum += cookie[right];
                } else {
                    break;
                }
            }
        }


        return answer;
    }
}
