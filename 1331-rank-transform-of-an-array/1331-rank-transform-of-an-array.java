class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] copy = Arrays.stream(arr).toArray();
        Arrays.sort(copy);

        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int n : copy) {
            if (!map.containsKey(n)) {
                map.put(n, rank);
                rank++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) map.get(arr[i]);
        }
        return arr;
    }
}