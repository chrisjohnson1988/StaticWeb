/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.image;

/**
 *
 * @author Heather
 */
public class Dimension2DIntegerPrecision {
    private final int height, width;
    
    public Dimension2DIntegerPrecision(int width, int height) {
        this.height = height;
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
}
