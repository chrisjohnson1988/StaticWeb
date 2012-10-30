/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.coffeescript;

import java.io.File;
import net.common.build.AbstractBulkFileCompilerTask;
import net.common.build.FileCompiler;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Chris Johnson
 */
public class BulkCoffeeScriptTask extends AbstractBulkFileCompilerTask {
    private static final CoffeeScript COFFEESCRIPT_COMPILER = new CoffeeScript();
    
    @Override
    protected FileCompiler getCompiler() {
        return COFFEESCRIPT_COMPILER;
    }

    @Override
    protected File getOutputFile(File in) {
        return new File(FilenameUtils.removeExtension(in.getAbsolutePath()) + ".js");
    }
}