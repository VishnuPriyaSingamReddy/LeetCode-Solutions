import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void addMap(TreeMap<Integer, Integer> map, int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        void removeMap(TreeMap<Integer, Integer> map, int x) {
            int c = map.get(x);
            if (c == 1) map.remove(x);
            else map.put(x, c - 1);
        }

        void add(int x) {
            if (lowSize == 0 || x <= low.lastKey()) {
                addMap(low, x);
                lowSize++;
                lowSum += x;
            } else {
                addMap(high, x);
                highSize++;
                highSum += x;
            }
            balance();
        }

        void remove(int x) {
            if (low.containsKey(x)) {
                removeMap(low, x);
                lowSize--;
                lowSum -= x;
            } else {
                removeMap(high, x);
                highSize--;
                highSum -= x;
            }
            balance();
        }

        void balance() {
            while (lowSize > highSize + 1) {
                int x = low.lastKey();
                removeMap(low, x);
                lowSize--;
                lowSum -= x;

                addMap(high, x);
                highSize++;
                highSum += x;
            }

            while (lowSize < highSize) {
                int x = high.firstKey();
                removeMap(high, x);
                highSize--;
                highSum -= x;

                addMap(low, x);
                lowSize++;
                lowSum += x;
            }
        }

        long cost() {
            long median = low.lastKey();

            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        // Calculate minimum cost of every window
        MedianWindow mw = new MedianWindow();

        for (int i = 0; i < x; i++) {
            mw.add(nums[i]);
        }

        cost[0] = mw.cost();

        for (int i = x; i < n; i++) {
            mw.remove(nums[i - x]);
            mw.add(nums[i]);
            cost[i - x + 1] = mw.cost();
        }

        // DP: minimum cost to select j windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip current element
                curr[i] = curr[i - 1];

                // Select window ending at i-1
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}