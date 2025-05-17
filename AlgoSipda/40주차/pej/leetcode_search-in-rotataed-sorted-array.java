// [LEETCODE] https://leetcode.com/problems/search-in-rotated-sorted-array/description/
// 이분탐색 변형 : 정렬된 공간에서 어떻게 이분탐색이 동작하는지 

class Solution {
    public int search(int[] nums, int target) {
        // 특정 피봇 기준으로 정렬되어 있음 
        // [(6), 7, 0, 1, 2, 4, 5] // case 1-1
        //  L          M        R
        // [1, 2, 4, 5, (6), 7, 0] // case 1-2 
        //  L        M          R
        // [ 5, 6, 7, (0), 1, 2, 4] // case 2-1
        //  L         M          R
        // [ 4, 5, (6), 7, 0, 1, 2] // case 2-2
        //  L         M          R
        
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } 
            if(nums[mid] >= nums[left]) {  // left sorted
                if(nums[left] <= target && target <= nums[mid]) {  //case 1-1
                    right = mid - 1;
                } else { // case 1-2
                    left = mid + 1;
                }
            } else { //right sorted
                if(nums[mid] <= target && target <= nums[right]) {  // case2-1
                    left = mid + 1;
                } else { // case2-2
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}