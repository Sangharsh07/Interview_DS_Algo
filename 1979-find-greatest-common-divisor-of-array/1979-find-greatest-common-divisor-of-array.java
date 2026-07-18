class Solution {
    public int findGCD(int[] nums) {
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;

        for(int n : nums){
            if(n < smallest) smallest = n;
            if(n > largest) largest = n;
        }

        return findGCD(smallest, largest);
    }

    public int findGCD(int a, int b){
        while(a != 0){
            int temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}