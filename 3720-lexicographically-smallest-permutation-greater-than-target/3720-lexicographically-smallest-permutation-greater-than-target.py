class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        n = len(s)

        cnt = [0] * 26

        for ch in s:
            cnt[ord(ch) - ord('a')] += 1

        # Try every position from right to left
        for i in range(n - 1, -1, -1):

            # Count characters remaining after matching target[0:i]
            count = cnt[:]

            possible = True

            for j in range(i):
                idx = ord(target[j]) - ord('a')

                if count[idx] == 0:
                    possible = False
                    break

                count[idx] -= 1

            if not possible:
                continue

            current = ord(target[i]) - ord('a')

            # Find smallest character greater than target[i]
            for j in range(current + 1, 26):
                if count[j] > 0:

                    count[j] -= 1

                    ans = target[:i] + chr(j + ord('a'))

                    # Smallest possible remaining suffix
                    for k in range(26):
                        ans += chr(k + ord('a')) * count[k]

                    return ans

        return ""