package com.console.canvas.service;

import org.junit.jupiter.api.Test;

/**
 * Unit test for ConsoleCommandService.
 */
class ConsoleCommandServiceTest {

    private ConsoleCommandService consoleCommandService = new ConsoleCommandService();

    @Test
    void createCanvasTest() {
        String[] testInput = {"20", "4"};
        consoleCommandService.createCanvas(testInput);
    }

    @Test
    void sequenceTest() {
        String[] canvasInput = {"20", "4"};
        consoleCommandService.createCanvas(canvasInput);
        String[] lineInput1 = {"1", "2", "6", "2"};
        consoleCommandService.drawLine(lineInput1);
        String[] testInput2 = {"6", "3", "6", "4"};
        consoleCommandService.drawLine(testInput2);
        String[] rectInput = {"14", "1", "18", "3"};
        consoleCommandService.drawRectangle(rectInput);
        String[] fillInput = {"10", "3", "o"};
        consoleCommandService.fill(fillInput);
        String[] lineInput3 = {"6", "4", "13", "4"};
        consoleCommandService.drawLine(lineInput3);
        String[] fillInput1 = {"1", "2", "."};
        consoleCommandService.fill(fillInput1);
    }
}
