package com.console.canvas.service;

import com.console.canvas.model.Canvas;

public class ConsoleCommandService {

    private static Canvas canvas;

    public void createCanvas(String[] params) {

        if (params != null && params.length == 2) {
            canvas = new Canvas(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
            for (int row = 0; row < canvas.getCanvasByteArray().length; row++) {
                for (int col = 0; col < canvas.getCanvasByteArray()[row].length; col++) {
                    if (row == 0 || row == canvas.getCanvasByteArray().length - 1) {
                        canvas.getCanvasByteArray()[row][col] = '-';
                    }
                    else if (col == 0 || col == canvas.getCanvasByteArray()[row].length - 1) {
                        canvas.getCanvasByteArray()[row][col] = '|';
                    }
                    else {
                        canvas.getCanvasByteArray()[row][col] = ' ';
                    }
                }
            }
            renderCanvas();
        }
        else {
            showConsoleMessage("Please input height and width to create canvas.");
        }
    }

    private void renderCanvas() {

        for (int i = 0; i < canvas.getHeight(); i++) {
            for (int j = 0; j < canvas.getWidth(); j++) {
                System.out.print((char) canvas.getCanvasByteArray()[i][j]);
            }
            System.out.println();
        }
    }

    private void showConsoleMessage(String message) {
        System.out.println(message);
    }

    public void drawLine(String[] params) {
        if (canvas != null) {
            if (params != null && params.length == 4) {
                int x0 = Integer.parseInt(params[0]);
                int x1 = Integer.parseInt(params[2]);
                int y0 = Integer.parseInt(params[1]);
                int y1 = Integer.parseInt(params[3]);
                for (int i = y0; i <= y1; i++) {
                    for (int j = x0; j <= x1; j++) {
                        if (isInsideCanvas(j, i)) {
                            canvas.getCanvasByteArray()[i][j] = 'x';
                        }
                    }
                }
                renderCanvas();
            }
            else {
                showConsoleMessage("Please input all the 4 coordinates to draw the line.");
            }
        }
        else {
            showConsoleMessage("Please create a canvas first.");
        }

    }

    private boolean isInsideCanvas(int x, int y) {
        return x > 0 && x < canvas.getWidth() - 1 && y > 0 && y < canvas.getHeight() - 1;
    }

    public void drawRectangle(String[] params) {
        if (canvas != null) {
            if (params != null && params.length == 4) {
                int x1 = Integer.parseInt(params[0]);
                int y1 = Integer.parseInt(params[1]);
                int x2 = Integer.parseInt(params[2]);
                int y2 = Integer.parseInt(params[3]);
                for (int i = y1; i <= y2; i++) {
                    for (int j = x1; j <= x2; j++) {
                        if ((i == y1 || i == y2) || (j == x1 || j == x2)) {
                            if (isInsideCanvas(j, i)) {
                                canvas.getCanvasByteArray()[i][j] = 'x';
                            }
                            else return;
                        }
                    }
                }
                renderCanvas();
            }
            else {
                showConsoleMessage("Please input all the 4 coordinates to draw the line.");
            }
        }
        else {
            showConsoleMessage("Please create a canvas first.");
        }

    }

    public void fill(String[] params) {
        if (canvas != null) {
            if (params != null && params.length == 3) {
                int x = Integer.parseInt(params[0]);
                int y = Integer.parseInt(params[1]);
                char newColor = params[2].charAt(0);
                byte currentColor;
                if (isInsideCanvas(x, y)) {
                    currentColor = canvas.getCanvasByteArray()[y][x];
                }

                else {
                    showConsoleMessage("(x,y) not inside the canvas");
                    return;
                }
                if (newColor == currentColor) {
                    return;
                }
                else bucketFill(x, y, (byte) newColor, currentColor);

                renderCanvas();
            }
            else {
                showConsoleMessage("Please input 2 coordinates and the color to fill.");
            }
        }
        else {
            showConsoleMessage("Please create a canvas first.");
        }

    }

    private void bucketFill(int x, int y, byte newColor, byte currColor) {

        if (x > 0 || y < canvas.getWidth() || y > 0 || x < canvas.getHeight()) {

            if (canvas.getCanvasByteArray()[y][x] == currColor) {
                canvas.getCanvasByteArray()[y][x] = newColor;
            }
            else return;
            bucketFill(x + 1, y, newColor, currColor);
            bucketFill(x - 1, y, newColor, currColor);
            bucketFill(x, y - 1, newColor, currColor);
            bucketFill(x, y + 1, newColor, currColor);

        }
    }

    public void handleInvalidInput() {
        System.out.println("|Command \t\t|Description|\n" + "|----|----|\n" + "|C w h          | Create a new canvas of width w and height h.|\n" + "|L x1 "
                + "y1" + " x2 y2  | Draw a new line from (x1,y1) to (x2,y2). Currently, only|\n" + "|               | horizontal or vertical lines are " +
                "supported. " + "Horizontal and vertical lines|\n" + "|               | will be drawn using the 'x' character.|\n" + "|R x1 y1 x2 y2  | Draw " +
                "" + "a rectangle " + "whose upper left corner is (x1,y1) and|\n" + "|               | lower right corner is (x2,y2). Horizontal and vertical" +
                " " + "lines will be drawn|\n" + "|               | using the 'x' character.|\n" + "|B x y c        | Fill the entire area connected to (x,y)" +
                " with " + "\"colour\" c. The|\n" + "|               | behaviour of this is the same as that of the \"bucket fill\" tool in paint|\n" + "|   " +
                "           " + " | programs.|\n" + "|Q              | Quit|");

    }
}