import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int q = queries.length;

        int[][] arr = new int[q][2];

        for(int i = 0; i < q; i++) {
            arr[i][0] = queries[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int freq[] = new int[n + 1];

        int ans[] = new int[q];

        int left = 0;
        int right = 0;
        int active = 0;

        for(int i = 0; i < q; i++) {

            int time = arr[i][0];

            // Add requests within the query's upper limit
            while(right < logs.length && logs[right][1] <= time) {

                int server = logs[right][0];

                if(freq[server] == 0)
                    active++;

                freq[server]++;
                right++;
            }

            // Remove requests outside the time window
            while(left < right && logs[left][1] < time - x) {

                int server = logs[left][0];

                freq[server]--;

                if(freq[server] == 0)
                    active--;

                left++;
            }

            ans[arr[i][1]] = n - active;
        }

        return ans;
    }
}