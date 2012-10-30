/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.sass;

import net.common.build.AbstractFileCompilerTask;
import net.common.build.FileCompiler;

/**
 *
 * @author Chris Johnson
 */
public class SassTask extends AbstractFileCompilerTask {
    private static final Sass SASS_COMPILER = new Sass();
    
    @Override
    protected FileCompiler getCompiler() {
        return SASS_COMPILER;
    }
}