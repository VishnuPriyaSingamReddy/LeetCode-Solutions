class Solution {
    public boolean sumGame(String num) {
        int n = num.length() / 2;
        int a = 0, b = 0, x = 0, y = 0;

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            if (c == '?') {
                if (i < n) x++;
                else y++;
            } else {
                if (i < n) a += c - '0';
                else b += c - '0';
            }
        }

        if (x == y)
            return a != b;

        if (x > y)
            return 2 * (a - b) != 9 * (y - x);

        return 2 * (b - a) != 9 * (x - y);
    }
}