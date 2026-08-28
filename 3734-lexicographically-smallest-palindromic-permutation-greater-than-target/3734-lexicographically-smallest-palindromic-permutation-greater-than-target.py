class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        
        count = [0] * 26

        for ch in s:
            count[ord(ch) - ord('a')] += 1

        # Check odd frequencies
        odd = 0
        middle = ""

        for i in range(26):
            if count[i] % 2 == 1:
                odd += 1
                middle = chr(ord('a') + i)

        if odd > 1:
            return ""

        # Characters available for the left half
        half = [count[i] // 2 for i in range(26)]

        left = []

        # Build the left half
        for _ in range(len(s) // 2):

            found = False

            # Try smallest character first
            for i in range(26):

                if half[i] == 0:
                    continue

                half[i] -= 1
                left.append(chr(ord('a') + i))

                # Build the largest possible completion
                temp = left.copy()

                for j in range(25, -1, -1):
                    temp.extend(
                        chr(ord('a') + j) * half[j]
                    )

                temp_left = "".join(temp)

                candidate = (
                    temp_left
                    + middle
                    + temp_left[::-1]
                )

                # Check if some valid answer is possible
                if candidate > target:
                    found = True
                    break

                # Undo
                left.pop()
                half[i] += 1

            if not found:
                return ""

        left = "".join(left)

        answer = left + middle + left[::-1]

        if answer > target:
            return answer

        return ""