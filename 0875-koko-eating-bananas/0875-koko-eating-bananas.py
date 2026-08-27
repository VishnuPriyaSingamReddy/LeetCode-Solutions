class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:

        left = 1
        right = max(piles)

        answer = right

        while left <= right:

            speed = (left + right) // 2

            hours = 0

            # Calculate total hours needed
            for pile in piles:
                hours += (pile + speed - 1) // speed

            # Speed works
            if hours <= h:
                answer = speed
                right = speed - 1

            # Speed is too slow
            else:
                left = speed + 1

        return answer