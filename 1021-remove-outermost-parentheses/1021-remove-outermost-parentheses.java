class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int depth = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // If depth > 0, this is not an outermost '('
                if (depth > 0) {
                    ans.append(ch);
                }
                depth++;
            }
            else {
                depth--;

                // If depth > 0, this is not an outermost ')'
                if (depth > 0) {
                    ans.append(ch);
                }
            }
        }

        return ans.toString();
    }
}