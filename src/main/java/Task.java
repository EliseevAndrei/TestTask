import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("enter sequence: ");
            validate(scan.nextLine()).forEach(System.out::println);
        }
    }

    private static Set<String> validate(String input) {
        int wrongLeftCount;
        int wrongRightCount = 0;
        int openedLeftCount = 0;
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '{':
                    openedLeftCount++;
                    break;
                case '}':
                    if (openedLeftCount == 0) {
                        wrongRightCount++;
                    } else openedLeftCount--;
            }
        }
        wrongLeftCount = openedLeftCount;

        Set<String> result = new HashSet<>();
        if (wrongRightCount == 0 && wrongLeftCount == 0) {
            result.add(input);
            return result;
        }
        StringBuilder sequence = new StringBuilder(input);
        checkBracket(0, 0, wrongLeftCount, wrongRightCount, sequence, result);
        return result;
    }


    private static void checkBracket(int openedLeftCount, int position, int leftCount, int rightCount, StringBuilder input, Set<String> result) {

        if (position >= input.length()) {
            if (openedLeftCount == 0 && leftCount == 0 && rightCount == 0) {
                result.add(input.toString());
                return;
            } else return;
        }

        StringBuilder sequence = new StringBuilder(input);
        while(position < sequence.length()) {
            switch (sequence.charAt(position)) {
                case '{' :
                    checkBracket(openedLeftCount + 1, position + 1, leftCount, rightCount, sequence, result);
                    sequence.deleteCharAt(position);
                    leftCount--;
                    break;
                case '}' :
                    if (openedLeftCount == 0) {
                        sequence.deleteCharAt(position);
                        rightCount--;
                    } else {
                        checkBracket(openedLeftCount - 1, position + 1, leftCount, rightCount, sequence, result);
                        sequence.deleteCharAt(position);
                        rightCount--;
                    }
                    break;
                    default: position++;
            }
            if (leftCount < 0 || rightCount < 0) {
                return;
            }
        }
        checkBracket(openedLeftCount, position, leftCount, rightCount, sequence, result);
    }
}
