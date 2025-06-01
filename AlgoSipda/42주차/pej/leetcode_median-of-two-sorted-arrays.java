// [LEETCODE] median-of-two-sorted-arrays  https://leetcode.com/problems/median-of-two-sorted-arrays/
// 이분 탐색 기반 : 두 배열 중 더 짧은 배열을 기준으로 이분탐색을 수행한다 
// 배열을 좌우로 나누어 left part와 right part를 만들고 왼쪽 최대값 <= 오른쪽 최소값을 만족시키는 지점을 찾는다 
// 짝수면 두 수의 평균, 홀수면 중간값을 반환한다 

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        while(low <= high) {
            int partitionX = (low + high) / 2; // nums1을 이분탐색으로 자르는 인덱스
            int partitionY = (x + y + 1) / 2 - partitionX; // nums2에서 자를 인덱스  // partitionX + partitionY = (m + n + 1) / 2
            // 왼쪽에 들어갈 총 개수 = 전체 개수의 절반 (또는 절반보다 1많음)을 보장해주기에 각 파티션의 앞/뒤 개수 불균형을 걱정할 필요가 없음 

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1]; // nums1의 왼쪽에서 가장 큰 값
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX]; // nums1의 오른쪽에서 가장 작은 값

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1]; // nums2의 왼쪽에서 가장 큰 값
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY]; // nums2의 오른쪽에서 가장 작은 값 

            // [LEFT] ... maxLeftX / maxLeftY | minRightX / minRightY ... [RIGHT]
            // 양쪽 배열을 반으로 나눴을 때 정렬된 상태를 유지하는지 확인 : 왼쪽 구간의 최댓값 <= 오른쪽 구간의 최솟값 
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    // 전체 원소 개수가 짝수 : 중간 두 값의 평균
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    // 전체 개수가 홀수 : 왼쪽의 가장 큰 값이 중앙값
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1; // 왼쪽으로 이동
            } else {
                low = partitionX + 1; // 오른쪽으로 이동
            }
        }
    }
    
    // 문제 의도 x : O(N + M)
    // static double mergeNPlusM(int[] nums1, int[] nums2){
    //     int N = nums1.length + nums2.length;
    //     int[] result = new int[N];
    //     int r = 0;
    //     int i = 0, j = 0;
    //     while(true){
    //         if(i >= nums1.length|| j >= nums2.length){
    //             break;
    //         }
            
    //         if(nums1[i] >= nums2[j]) {
    //             result[r++] = nums2[j];
    //             j++;
    //         }else {
    //             result[r++] = nums1[i];
    //             i++;
    //         }
            
          
    //     }
        
    //     while(i < nums1.length){
    //         result[r++] = nums1[i];
    //         i++;
    //     }
        
    //     while(j < nums2.length) {
    //         result[r++] = nums2[j];
    //         j++;
    //     }

    //     double answer = 0;
    //     int mid = N / 2;
    //     if(N%2!=0) {
    //         return (double)result[mid];
    //     }
    
    //     return (double)(result[mid] + result[mid-1]) / 2;
    // }
}
