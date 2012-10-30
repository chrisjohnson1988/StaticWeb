/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.sass;

import net.common.build.CompilationException;

/**
 *
 * @author Chris Johnson
 */
public class SassException extends CompilationException {

    public SassException(String mess, Throwable ex) {
        super(mess,ex);
    }
    
}
