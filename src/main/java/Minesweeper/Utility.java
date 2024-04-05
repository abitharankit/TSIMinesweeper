package Minesweeper;

import java.util.LinkedList;
import java.util.Scanner;

public class Utility {
    public static String padding(String text, int size){
        int length = text.length();
        String paddedText = "";
        int neededPadding = size - length;
        paddedText = paddedText + " ".repeat(neededPadding/2);
        paddedText += text;
        paddedText = paddedText + " ".repeat(neededPadding/2);
        paddedText = paddedText + " ".repeat(neededPadding%2);
        return paddedText;
    }


    public static int userInputInt(String prompt, int option) {
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        System.out.println(prompt);
        //1 for any integer
        if (option == 1) {
            while (true) {
                try {
                    userInput = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Not a valid integer. Please try again: ");
                }
            }
        } else if (option == 2) { // for positive integer 0 exclusive
            while (true) {
                try {
                    userInput = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Not a valid non-zero integer. Please try again: ");
                }
            }
            while (userInput <= 0) {
                System.out.println("Input must be a positive non-zero integer. Please try again: ");
                while (true) {
                    try {
                        userInput = Integer.parseInt(scan.nextLine());
                        break;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Not a valid non-zero integer. Please try again: ");
                    }
                }
            }
        }  if (option == 3) { // for positive integer 0 inclusive
            while (true) {
                try {
                    userInput = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Not a valid integer. Please try again: ");
                }
            }
            while (userInput < 0) {
                System.out.println("Input must be a positive integer. Please try again: ");
                while (true) {
                    try {
                        userInput = Integer.parseInt(scan.nextLine());
                        break;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Not a valid integer. Please try again: ");
                    }
                }
            }
        }
        return userInput;
    }

    public static int userInputIntRange(String prompt,int lowerBound, int upperBound) {
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        System.out.println(prompt);
        while (true) {
            try {
                userInput = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Not a valid integer. Please try again: ");
            }
        }
        while (userInput < lowerBound || userInput > upperBound) {
            System.out.println("Input must be an integer between " + lowerBound + " and " + upperBound +
                    ". Please try again: ");
            while (true) {
                try {
                    userInput = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Not a valid integer. Please try again: ");
                }
            }
        }

        return userInput;
    }
    public static String userInputChar(String prompt, LinkedList chars) {
        Scanner scan = new Scanner(System.in);
        String userInput;
        System.out.println(prompt);
        userInput = scan.nextLine();
        if (!chars.contains(userInput.toUpperCase())) {
            while (!chars.contains(userInput.toUpperCase())) {
                System.out.println("Not a valid option. Please try again: ");
                userInput = scan.nextLine();
            }
        }
        return userInput;
    }
}
