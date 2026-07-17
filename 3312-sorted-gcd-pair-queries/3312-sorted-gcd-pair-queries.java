class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Count the frequency of each number
        long[] count = new long[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        // Array to store the exact number of pairs having a specific GCD
        long[] exactGcdPairs = new long[maxVal + 1];

        // Calculate pairs from maxVal down to 1
        for (int i = maxVal; i >= 1; i--) {
            long multiplesCount = 0;
            // Count all elements in the array that are multiples of i
            for (int j = i; j <= maxVal; j += i) {
                multiplesCount += count[j];
            }

            // Total pairs formed by multiples of i
            long pairs = multiplesCount * (multiplesCount - 1) / 2;

            // Subtract pairs that have a GCD which is a strict multiple of i
            for (int j = 2 * i; j <= maxVal; j += i) {
                pairs -= exactGcdPairs[j];
            }

            exactGcdPairs[i] = pairs;
        }

        // Prefix sums of the exact pair counts
        long[] prefix = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefix[i] = prefix[i - 1] + exactGcdPairs[i];
        }

        // Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];

            int left = 1;
            int right = maxVal;
            int res = maxVal;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                // We want the smallest 'mid' such that prefix[mid] > q
                if (prefix[mid] > q) {
                    res = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            answer[i] = res;
        }

        return answer;
    }
}