/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.coffeescript;

import net.common.build.AbstractFileCompilerTask;
import net.common.build.FileCompiler;

/**
 *
 * @author Chris Johnson
 */
public class CoffeeScriptTask extends AbstractFileCompilerTask {
    private static final CoffeeScript COFFEESCRIPT_COMPILER = new CoffeeScript();
    
    @Override
    protected FileCompiler getCompiler() {
        return COFFEESCRIPT_COMPILER;
    }
}
