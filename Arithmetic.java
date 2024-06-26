package calculator;

//This class handles all of the math or ARITHMETIC
public class Arithmetic {

    public void arithmetic (String[] equation, Variables variables) {
        int sum = 0;
        boolean add = true;

        if (equation.length == 1) {
            int num = Integer.parseInt(equation[0]);
            System.out.println(num);
        } else if (equation.length >= 2) {

            for (String token : equation) {

                if (token.equals("+")) {
                    add = true;
                } else if (token.equals("-")) {
                    add = false;
                } else {
                    try {
                        int num = getNumber(token, variables);
                        if (add) {
                            sum += num;
                        } else {
                            sum -= num;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Number " + token);
                    }
                }
            }
            System.out.println(sum);
            sum = 0;
        }
    }
    private int getNumber(String token, Variables variables) throws NumberFormatException {
        if (variables.containsVariable(token)) {
            return variables.getVariable(token);
        } else {
            return Integer.parseInt(token);
        }
    }
}