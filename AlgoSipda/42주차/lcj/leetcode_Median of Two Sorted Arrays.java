class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            int px = (low + high) / 2;
            int py = (m + n + 1) / 2 - px;

            int maxlx = (px == 0) ? Integer.MIN_VALUE : nums1[px - 1];
            int minrx = (px == m) ? Integer.MAX_VALUE : nums1[px];

            int maxly = (py == 0) ? Integer.MIN_VALUE : nums2[py - 1];
            int minry = (py == n) ? Integer.MAX_VALUE : nums2[py];

            if (maxlx <= minry && maxly <= minrx) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxlx, maxly) + Math.min(minrx, minry)) / 2.0;
                } else {
                    return Math.max(maxlx, maxly);
                }
            } else if (maxlx > minry) {
                high = px - 1;
            } else {
                low = px + 1;
            }
        }
        return 0.0;
    }
}