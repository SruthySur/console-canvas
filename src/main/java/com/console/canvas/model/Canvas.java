/*
 * Canvas class
 */
package com.console.canvas.model;

public class Canvas {

    private int height;
    private int width;
    private byte[][] canvasByteArray;

    /**
     * Canvas
     *
     * @param width  int
     * @param height int
     */
    public Canvas(int width, int height) {
        setHeight(height + 2);
        setWidth(width);
        setCanvasByteArray(new byte[getHeight()][getWidth()]);
    }

    public int getHeight() {
        return this.height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public byte[][] getCanvasByteArray() {
        return this.canvasByteArray;
    }

    private void setCanvasByteArray(byte[][] canvasByteArray) {
        this.canvasByteArray = canvasByteArray;
    }

}
