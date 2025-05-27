class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums1) {
            list.add(n);
        }

        for (int n : nums2) {
            list.add(n);
        }

        Collections.sort(list);
        if (list.size() % 2 == 1)
            return (double) list.get(list.size() / 2);

        return ((double) list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;

    }
}