import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000;

        int[] best = new int[n];
        Arrays.fill(best, INF);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (map.containsKey(sum - target)) {
                int start = map.get(sum - target);
                int len = i - start;

                if (start >= 0 && best[start] != INF) {
                    answer = Math.min(answer, len + best[start]);
                }

                minLength = Math.min(minLength, len);
            }

            best[i] = minLength;
            map.put(sum, i);
        }

        return answer == INF ? -1 : answer;
    }
}