/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.sass;

import java.io.File;
import net.common.build.AbstractBulkFileCompilerTask;
import net.common.build.FileCompiler;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Chris Johnson
 */
public class BulkSassTask extends AbstractBulkFileCompilerTask {
    private static final Sass SASS_COMPILER = new Sass();
    
    @Override
    protected FileCompiler getCompiler() {
        return SASS_COMPILER;
    }

    @Override
    protected File getOutputFile(File in) {
        return new File(FilenameUtils.removeExtension(in.getAbsolutePath()) + ".css");
    }
}