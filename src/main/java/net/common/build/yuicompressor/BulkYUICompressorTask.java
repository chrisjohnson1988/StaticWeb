/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.yuicompressor;

import java.io.File;
import net.common.build.AbstractBulkFileCompilerTask;
import net.common.build.FileCompiler;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

/**
 *
 * @author Chris Johnson
 */
public class BulkYUICompressorTask extends AbstractBulkFileCompilerTask implements ErrorReporter{
    private FileCompiler compressor;

    public void setMode(YUICompressionMode mode) {
        this.compressor = mode.getYuiCompressor(this);
    }

    @Override
    protected FileCompiler getCompiler() {
        return compressor;
    }    
    
    @Override
    protected boolean isReady() {
        return compressor!=null;
    }
    
    @Override
    protected File getOutputFile(File in) {
        return in;
    }
    
    public void warning(String message, String sourceName, int line, java.lang.String lineSource, int lineOffset) {
        log(message);
    }

    public void error(String message, String sourceName, int line, java.lang.String lineSource, int lineOffset) {
        log(message);
    }

    public EvaluatorException runtimeError(String message, String sourceName, int line, java.lang.String lineSource, int lineOffset) {
        log(message);
        return new EvaluatorException(message);
    }
}