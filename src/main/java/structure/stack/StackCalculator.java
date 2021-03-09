package structure.stack;

import java.math.BigDecimal;

/**
 * 用两个栈实现的计算器
 */
public class StackCalculator {

    /**
     * 表达式
     */
    private String expr;

    /**
     * 操作数栈
     */
    private Stack<Number> numStack;

    /**
     * 表达式栈
     */
    private Stack<String> exprStack;

    /**
     * 操作符栈
     */
    private Stack<Character> operStack;

    public StackCalculator(String expr) {
        //去除表达式中的空格
        this.expr = expr.replace(" ", "");
        numStack = new Stack<>();
        operStack = new Stack<>();
        exprStack = new Stack<>();
    }

    /**
     * 中缀表达式转前缀表达式
     */
    public String infix2Prefix() {
        char[] chars = expr.toCharArray();
        //入栈
        for (int i = 0; i < chars.length; i++) {
            //如果是数字则直接入栈
            if (Character.isDigit(chars[i])) {
                String numString = String.valueOf(chars[i]);
                //此处考虑多位数和小数的情况 如果下一位还是数字或者小数点则继续向下取值
                while (i + 1 < chars.length &&(Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                    i++;
                    numString += chars[i];
                }
                exprStack.push(numString);
                continue;
            }
            //如果是运算符号 我们就需要在入栈的时候检查优先级顺序
            //1、如果operStack里面没有数据则直接入栈
            //2、如果operStack里面有数据，则需要比较栈顶运算符与本次运算符的优先级
            //2.1、如果本次优先级比较大，则放入栈
            //2.2、如果栈里面的优先级比较大，则从operStack取出栈顶运算符，从exprStack取出两个表达式进行拼接，再把运算结果放回exprStack
            //3、如果本次运算符是左括号，则放入栈
            //4、如果本次运算符是右括号，则不停地从operStack弹出运算符直到弹出左括号，把这两个括号中间的表达式算出结果，放入exprStack
            if (operStack.size() == 0) {
                operStack.push(chars[i]);
                continue;
            }

            if (chars[i] == '(') {
                operStack.push(chars[i]);
                continue;
            }
            if (chars[i] == ')') {
                while (operStack.top() != '(') {
                    getSuffix();
                }
                operStack.pop();
                continue;
            }
            if (compareOper(chars[i], operStack.top())) {
                operStack.push(chars[i]);
            } else {
                getSuffix();
                operStack.push(chars[i]);
            }
        }

        //出栈得出最后计算结果

        while (operStack.size() != 0) {
            getSuffix();
        }
        return exprStack.pop();
    }

    private void getPrefix() {
        String expr2 = exprStack.pop();
        String expr1 = exprStack.pop();
        Character oper = operStack.pop();
        //前缀表达式 符号在前
        String tempResult = oper + " " + expr1 + " " + expr2;
        exprStack.push(tempResult);
    }

    private void getSuffix() {
        String expr2 = exprStack.pop();
        String expr1 = exprStack.pop();
        Character oper = operStack.pop();
        //后缀表达式 符号在前
        String tempResult = expr1 + " " + expr2 + " " + oper;
        exprStack.push(tempResult);
    }

    /**
     * 计算表达式的值
     */
    public Number calculate() {
        char[] chars = expr.toCharArray();

        //入栈
        for (int i = 0; i < chars.length; i++) {
            //如果是数字则直接入栈
            if (Character.isDigit(chars[i])) {
                String numString = String.valueOf(chars[i]);
                //此处考虑多位数和小数的情况 如果下一位还是数字或者小数点则继续向下取值
                while (i + 1 < chars.length && (Character.isDigit(chars[i + 1]) || chars[i + 1] == '.')) {
                    i++;
                    numString += chars[i];
                }
                numStack.push(new BigDecimal(numString));
                continue;
            }

            //如果是运算符号 我们就需要在入栈的时候检查优先级顺序
            //1、如果operStack里面没有数据则直接入栈
            //2、如果operStack里面有数据，则需要比较栈顶运算符与本次运算符的优先级
            //2.1、如果本次优先级比较大，则放入栈
            //2.2、如果栈里面的优先级比较大，则从operStack取出栈顶运算符，从numStack取出两个数字进行运算，再把运算结果放回numStack
            //3、如果本次运算符是左括号，则放入栈
            //4、如果本次运算符是右括号，则不停地从operStack弹出运算符直到弹出左括号，把这两个括号中间的表达式算出结果，放入numStack
            if (operStack.size() == 0) {
                operStack.push(chars[i]);
                continue;
            }

            if (chars[i] == '(') {
                operStack.push(chars[i]);
                continue;
            }
            if (chars[i] == ')') {
                while (operStack.top() != '(') {
                    calResult();
                }
                operStack.pop();
                continue;
            }
            if (compareOper(chars[i], operStack.top())) {
                operStack.push(chars[i]);
            } else {
                calResult();
                operStack.push(chars[i]);
            }
        }

        //出栈得出最后计算结果

        while (operStack.size() != 0) {
            calResult();
        }
        return numStack.pop();
    }

    /**
     * 这个方法比较重要，巧妙的设计能够简便我们的运算
     * 当oper1和oper2的优先级一样的时候返回false，这样子就能保证在不考虑括号的情况下 operStack里面的数据量不超过2个
     * <p>
     * 1、stackOper是乘除法 返回false
     * 2、putOper和stackOper同为加减法 返回false
     * 3、stackOper是左括号 返回true
     * 4、其他情况(putOper是乘除法  stackChar是加减法) 返回true
     */
    private boolean compareOper(char putOper, char stackOper) {
        if (stackOper == '*' || stackOper == '/') {
            return false;
        }
        if ((putOper == '+' || putOper == '-') && (stackOper == '+' || stackOper == '-')) {
            return false;
        }
        if (stackOper == '(') {
            return true;
        }
        return true;
    }

    /**
     * 计算一次值 operStack取出一个值 numStack取出两个值 算好以后放进numStack
     */
    private void calResult() {
        Character oper = operStack.pop();
        //要注意第二次取出来的是 第一个操作数
        Number num2 = numStack.pop();
        Number num1 = numStack.pop();
        BigDecimal result = null;
        if (oper == '+') {
            result = new BigDecimal(String.valueOf(num1)).add(new BigDecimal(String.valueOf(num2)));
        } else if (oper == '-') {
            result = new BigDecimal(String.valueOf(num1)).subtract(new BigDecimal(String.valueOf(num2)));
        } else if (oper == '*') {
            result = new BigDecimal(String.valueOf(num1)).multiply(new BigDecimal(String.valueOf(num2)));
        } else if (oper == '/') {
            result = new BigDecimal(String.valueOf(num1)).divide(new BigDecimal(String.valueOf(num2)), BigDecimal.ROUND_HALF_UP);
        }
        numStack.push(result);
    }

    public static void main(String[] args) {
//        StackCalculator stackCalculator = new StackCalculator("(10.5+2.5)*(30/6+12.5/2.5*2)");
        StackCalculator stackCalculator = new StackCalculator("+*1 2 3");
//        System.out.println(stackCalculator.calculate());
        System.out.println(stackCalculator.infix2Prefix());
    }
}
