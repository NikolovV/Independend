package Part_I.Exercise._2_Parentheses;

import java.util.Scanner;

public class Parentheses {
    private static final StringBuilder sb = new StringBuilder();
    private static final StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int parenthesesCount = Integer.parseInt(in.nextLine());

        sb.append("(");
        generateParentheses(parenthesesCount - 1, 1);
        System.out.print(result);
    }

    private static void generateParentheses(int openingParentheses, int closingParentheses) {
        if (openingParentheses == 0 && closingParentheses == 0) {
            result.append(sb).append(System.lineSeparator());
            return;
        }

        if (openingParentheses == -1 || closingParentheses == -1) {
            return;
        }

        sb.append("(");
        generateParentheses(openingParentheses - 1, closingParentheses + 1);
        sb.replace(sb.length() - 1, sb.length(), ")");
        generateParentheses(openingParentheses, closingParentheses - 1);
        sb.delete(sb.length() - 1, sb.length());
    }
}
