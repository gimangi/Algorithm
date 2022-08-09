package BOJ_1918;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inorder = sc.next();
        String postorder = "";

        Stack<Character> stack = new Stack<>();

        for (int i=0; i<inorder.length(); i++) {
            char cur = inorder.charAt(i);

            if ('A' <= cur && cur <= 'Z') {
                postorder = postorder.concat(String.valueOf(cur));
            } else if (cur == ')') {
                while (!stack.empty()) {
                    char op = stack.pop();
                    if (op == '(')
                        break;

                    postorder = postorder.concat(String.valueOf(op));
                }
            } else {
                int priority = getPriority(cur);
                if (priority != -1) {
                    while (!stack.empty() && priority <= getPriority(stack.peek())) {
                        char op = stack.pop();
                        postorder = postorder.concat(String.valueOf(op));
                    }
                }
                stack.push(cur);
            }
        }

        while (!stack.empty()) {
            postorder = postorder.concat(String.valueOf(stack.pop()));
        }

        System.out.println(postorder);
    }

    static int getPriority(char op) {
        if (op == '+' || op == '-')
            return 1;
        else if (op == '*' || op == '/')
            return 2;
        return -1;
    }
}
