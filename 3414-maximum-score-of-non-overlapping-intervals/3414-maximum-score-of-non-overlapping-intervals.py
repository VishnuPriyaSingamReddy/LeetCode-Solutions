from bisect import bisect_left

class Solution:
    def maximumWeight(self, intervals):
        a = sorted((r, l, w, i) for i, (l, r, w) in enumerate(intervals))
        n = len(a)
        ends = [x[0] for x in a]

        prev = []
        for i in range(n):
            prev.append(bisect_left(ends, a[i][1]) - 1)

        dp = [[(0, ()) for _ in range(5)] for _ in range(n + 1)]

        def better(a, b):
            if a[0] != b[0]:
                return a if a[0] > b[0] else b
            return a if a[1] < b[1] else b

        for i in range(1, n + 1):
            r, l, w, idx = a[i - 1]

            for k in range(1, 5):
                skip = dp[i - 1][k]

                p = prev[i - 1]
                score, ids = dp[p + 1][k - 1]

                take = (
                    score + w,
                    tuple(sorted(ids + (idx,)))
                )

                dp[i][k] = better(skip, take)

        return list(dp[n][4][1])