package com.console.canvas;

import com.console.canvas.service.ConsoleCommandService;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    private static Scanner scanner;

    public static void main(String[] args) throws NumberFormatException {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command: ");
            new App().processCommand(scanner.nextLine().trim());
        }
    }

    private void processCommand(String userInput) throws NumberFormatException {
        ConsoleCommandService consoleCommandService = new ConsoleCommandService();

        if (userInput != null) {
            userInput = userInput.trim();
            String[] split = userInput.split("[ \t]+");
            String command = split[0].toUpperCase();
            String[] params = Arrays.copyOfRange(split, 1, split.length);

            try {
                switch (command) {
                    case "Q":
                        scanner.close();
                        System.out.println("````");
                        System.exit(0);
                    case "C":
                        consoleCommandService.createCanvas(params);
                        break;
                    case "L":
                        consoleCommandService.drawLine(params);
                        break;
                    case "R":
                        consoleCommandService.drawRectangle(params);
                        break;
                    case "B":
                        consoleCommandService.fill(params);
                        break;
                    default:
                        consoleCommandService.handleInvalidInput();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else consoleCommandService.handleInvalidInput();
    }

}
