class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        // Find the maximum pile size
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // Binary search for the minimum valid eating speed
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, h, mid)) {
                right = mid;       // Try a smaller speed
            } else {
                left = mid + 1;    // Need a faster speed
            }
        }

        return left;
    }

    private boolean canFinish(int[] piles, int h, int speed) {
        long hours = 0;

        for (int pile : piles) {
            // Ceiling division: ceil(pile / speed)
            hours += (pile + speed - 1) / speed;

            if (hours > h) {
                return false;
            }
        }

        return true;
    }
}