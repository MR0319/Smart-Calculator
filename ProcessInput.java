package calculator;

public class ProcessInput {

    private Manual helpmanual;
    Arithmetic arithmetic = new Arithmetic();

    public void handleInput(String userInput, Variables variables) {

        if (userInput.isEmpty()) {
            return;
        }

        userInput = userInput.replaceAll("--", "+");
        userInput = userInput.replaceAll("-\\s+", "-");

        String[] equation = userInput.split("(?<=[-+])|(?=[-+])");

        for(int i = 0; i < equation.length; i++) {
            equation[i] = equation[i].trim();
            if (variables.containsVariable(equation[i])) {
                equation[i] = variables.getVariable(equation[i]).toString();
            }
        }

        arithmetic.arithmetic(equation, variables);


    }
}
