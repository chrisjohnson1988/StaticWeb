/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.coffeescript;

import net.common.build.CompilationException;
import org.mozilla.javascript.JavaScriptException;

/**
 *
 * @author Chris Johnson
 */
public class JCoffeeScriptCompileException extends CompilationException {

    public JCoffeeScriptCompileException(JavaScriptException e) {
        super(e);
    }
}
