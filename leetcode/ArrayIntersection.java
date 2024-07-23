import java.util.HashSet;

class ArrayIntersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        // cada elemento de los HashSets son únicos
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> intersection = new HashSet<>();

        for (Integer nums : nums1) {
            hashSet.add(nums);
        }
        for (Integer nums : nums2) {
            if (hashSet.contains(nums)) {
                intersection.add(nums);
            }
        }

        int[] intersected = new int[intersection.size()];
        int index = 0;
        for (Integer nums : intersection) {
            intersected[index++] = nums;
        }

        return intersected;
    }
}