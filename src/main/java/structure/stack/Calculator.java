package structure.stack;

import lombok.Data;

@Data
public class Calculator {

    private ArrayStack<Integer> numStack = new ArrayStack(10);

    private ArrayStack<Character> operStack = new ArrayStack(10);

    private String expression;

    Calculator(String expression) {
        this.expression = expression.replace(" ", "");
    }

    public int calculate() {
        char[] chars = expression.toCharArray();
        //第一个循环 把表达式拆解分别入两个栈
        //在入栈的时候 必须要确保高优先级的运算位于栈顶
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (Character.isDigit(aChar)) {
                //数字则直接入栈
                //考虑一下 是否是多位数
                StringBuilder num = new StringBuilder("");
                int j = i;
                while (true) {
                    if (j <= chars.length - 1 && Character.isDigit(chars[j])) {
                        num.append(chars[j]);
                    } else {
                        //如果最后一个不是数字 则需要回退一个 因为i要跟着j走
                        j--;
                        break;
                    }
                    j++;
                }
                i = j;
                numStack.push(Integer.valueOf(num.toString()));
            } else {
                //操作符则需要进行优先级比较
                //如果operStack里面没有或者优先级比当前的优先级小则直接入栈
                //如果operStack里面的操作符比当前的优先级大  则pop出来 并且从numStack中pop出两个数字进行运算  再把运算结果push回numStack
                if (operStack.isEmpty()) {
                    operStack.push(aChar);
                } else {
                    Character top = operStack.top();
                    if (aChar==')') {
                        calTempResult();
                    } else if (compareOper(top, aChar)) {
                        Integer num2 = numStack.pop();//先出栈的是被运算的数
                        Integer num1 = numStack.pop();
                        Character pop = operStack.pop();
                        int tempResult = cal(pop, num1, num2);
                        operStack.push(aChar);
                        numStack.push(tempResult);
                    } else {
                        operStack.push(aChar);
                    }
                }
            }
        }

        //第二次循环 循环计算栈里面的数据
        while (true) {
            if (numStack.size() == 1) {
                break;
            }
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            Character pop = operStack.pop();
            int tempResult = cal(pop, num1, num2);
            numStack.push(tempResult);
        }
        return numStack.top();
    }

    /**
     * 遇到括号则优先计算括号里面的数据
     * 触发时机是 遇到")"的时候
     */
    private void calTempResult() {
        while (true) {
            Character pop = operStack.pop();
            if (pop.equals('(')) {
                break;
            }
            Integer num2 = numStack.pop();
            Integer num1 = numStack.pop();
            int tempResult = cal(pop, num1, num2);
            numStack.push(tempResult);
        }
    }

    private int cal(Character oper, int num1, int num2) {
        int result;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                result = 99999;
        }
        return result;
    }

    /**
     * 比较两个操作符的优先级
     */
    private boolean compareOper(char oper1, char oper2) {
        if (oper1 == '(') {
            return false;
        }
        if (oper2 == '(') {
            return false;
        }
        if (oper1 == '*' || oper1 == '/') {
            return true;
        } else {
            return oper2 == '+' || oper2 == '-';
        }
    }

}
