/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build;

/**
 *
 * @author Chris Johnson
 */
public class CompilationException extends Exception {
    /**
     * Constructs an instance of <code>CompilationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CompilationException(String msg) {
        super(msg);
    }
    
    public CompilationException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public CompilationException(Throwable cause) {
        super(cause);
    }
}
