class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int maxSoFar = 0;
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, nums[i]);
            nums[i] = findGCD(nums[i], maxSoFar);
        }

        Arrays.sort(nums);
        long sum = 0;
        for (int i = 0; i < n / 2; i++) {
            sum += findGCD(nums[i], nums[n - i - 1]);
        }
        return sum;
    }

    public int findGCD(int a, int b) {
        while (a != 0) {
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}