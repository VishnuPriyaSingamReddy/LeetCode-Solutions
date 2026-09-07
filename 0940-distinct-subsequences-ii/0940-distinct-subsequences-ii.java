class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        // dp = number of distinct subsequences including empty subsequence
        long dp = 1;

        // last[c] = dp value before the previous occurrence of c
        long[] last = new long[26];

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            long newDp = (2 * dp) % MOD;

            // Remove duplicates caused by previous occurrence of this character
            newDp = (newDp - last[c] + MOD) % MOD;

            // Store current dp for future duplicate removal
            last[c] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}