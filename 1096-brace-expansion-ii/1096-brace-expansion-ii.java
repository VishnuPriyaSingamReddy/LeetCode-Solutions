import java.util.*;

class Solution {
    int pos = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);

        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: a,b
    Set<String> parse(String s) {
        Set<String> result = parseConcat(s);

        while (pos < s.length() && s.charAt(pos) == ',') {
            pos++;
            result.addAll(parseConcat(s));
        }

        return result;
    }

    // Handles concatenation: ab
    Set<String> parseConcat(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (pos < s.length()
                && s.charAt(pos) != '}'
                && s.charAt(pos) != ',') {

            Set<String> part;

            if (s.charAt(pos) == '{') {
                pos++; // skip {

                part = parse(s);

                pos++; // skip }
            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(pos)));
                pos++;
            }

            result = multiply(result, part);
        }

        return result;
    }

    // Concatenate two sets
    Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}