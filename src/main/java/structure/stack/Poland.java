package structure.stack;

import java.util.Arrays;

public class Poland {

    /**
     * 中缀表达式转后缀表达式 1+3*(7-4) ==> 1 3 7 4 -  *  +
     * 后缀表达式特点：数字在前 两个要运算数字的后面紧跟运算符
     */
    public void infix2Suffix(String expression) {
        //简单处理原中缀表达式 默认使用空格分开每一个数字
        String[] splitList = expression.split(" ");

        //结果栈
        String tempResult = "";

        //操作数栈
        ArrayStack<String> numStack = new ArrayStack<>(100);

        //操作符栈
        ArrayStack<String> operStack = new ArrayStack<>(100);

        for (String s : splitList) {
            if (isOper(s)) {
                if (!s.equals("(") && !s.equals(")")) {
                    //首先考虑当前符号是非括号的情况
                    //如果operStack里面没有操作符或者operStack.top().equals("(") 或者 当前符号的优先级比operStack.top()大（严格大） 则直接放入
                    if (operStack.isEmpty() || operStack.top().equals("(") || compareOper(s, operStack.top())) {
                        operStack.push(s);
                    } else {
                        //如果当前符号的优先级没有比operStack.top()大 则把numStack里面两个出栈 operStack再出栈 赋值给tempResult再push回numStack
                        String num2 = numStack.pop();
                        String num1 = numStack.pop();
                        String oper = operStack.pop();
                        tempResult = num1 + " " + num2 + " " + oper;
                        numStack.push(tempResult);
                        operStack.push(s);
                    }
                } else {
                    //接着考虑括号的情况
                    //如果是(则直接放进去
                    //如果是),则需要一直调用operStack.pop()直到遇到(为止 把其中的数据直接变成后缀表达式
                    if (s.equals("(")) {
                        operStack.push(s);
                    } else if (s.equals(")")) {
                        while (true) {
                            String oper = operStack.pop();
                            if (oper.equals("(")) {
                                break;
                            }
                            String num2 = numStack.pop();
                            String num1 = numStack.pop();
                            tempResult = num1 + " " + num2 + " " + oper;
                            numStack.push(tempResult);
                        }
                    }
                }
            } else {
                //如果是数字 则直接放到numStack里面
                numStack.push(s);
            }
        }

        //把两个栈里面的数据整理成最终的结果
        while (true) {
            if (numStack.size() == 1) {
                System.out.println("最终结果为:" + numStack.pop());
                break;
            }
            String num2 = numStack.pop();
            String num1 = numStack.pop();
            String oper = operStack.pop();
            tempResult = num1 + " " + num2 + " " + oper;
            numStack.push(tempResult);
        }
    }

    /**
     * 判断是否为运算符
     */
    private boolean isOper(String s) {
        return Arrays.asList("+", "-", "*", "/", "(", ")").contains(s);
    }

    /**
     * 比较两个操作符的优先级
     */
    private boolean compareOper(String oper1, String oper2) {
        return (oper1.equals("*") || oper1.equals("/")) && (oper2.equals("+") || oper2.equals("-"));
    }

    public static void main(String[] args) {
        Poland poland = new Poland();
        poland.infix2Suffix("( 1 + 2 ) * 3");
        poland.infix2Suffix("1 + 3 * ( 7 - 4 )");
        poland.infix2Suffix("1 + ( ( 2 + 3 ) * 4 ) - 5");

    }
}
