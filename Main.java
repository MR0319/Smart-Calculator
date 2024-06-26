package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProcessInput processInput = new ProcessInput();
        Manual manual = new Manual();
        Variables variables = new Variables();

        while (true){
            String userInput = scanner.nextLine();

            if (userInput.equals("/exit")) {
                System.out.println("Bye!");
                break;
            } else if (userInput.equals("/help")) {
                manual.helpmanual();
                continue;
            } else if (userInput.matches("([a-zA-Z0-9]*[+|\\-])")){
                manual.invalidExpression();
                continue;
            } else if (userInput.matches("/[a-zA-Z0-9]*")) {
                manual.unknownCommand();
            } else if (userInput.matches("[a-zA-Z]+\\s*=\\s*\\d+")) {
                String[] parts = userInput.split("\\s*=\\s*");
                String varName = parts[0];
                int value = Integer.parseInt(parts[1]);
                variables.setVariable(varName, value);
                continue;
            } else if (userInput.matches("[a-zA-Z]+\\s*=\\s*[a-zA-Z]+")) {
                String[] parts = userInput.split("\\s*=\\s*");
                String varName = parts[0];
                String referencedVariable = parts[1].trim();
                if (variables.containsVariable(referencedVariable)) {
                    int value = variables.getVariable(referencedVariable);
                    variables.setVariable(varName, value);
                } else {
                    manual.unknownVariable();
                }
                continue;
            } else if (!userInput.isEmpty()) {
                String[] tokens = userInput.split("[\\s+\\-*/]+");
                boolean unknownVariable = false;
                for (String token : tokens) {
                    if (token.matches("[a-zA-Z]+") && !variables.containsVariable(token)) {
                        manual.unknownVariable();
                        unknownVariable = true;
                        break;
                    }
                }
                if (unknownVariable) {
                    continue;
                }
                processInput.handleInput(userInput, variables);
            }

        }
    }
}