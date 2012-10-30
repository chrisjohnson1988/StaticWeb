package net.common.build.yuicompressor;

import net.common.build.AbstractFileCompilerTask;
import net.common.build.FileCompiler;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

/**
 *
 * @author Administrator
 */
public class YUICompressorTask extends AbstractFileCompilerTask implements ErrorReporter{
    private FileCompiler compressor;

    public void setMode(YUICompressionMode mode) {
        this.compressor = mode.getYuiCompressor(this);
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

    @Override
    protected FileCompiler getCompiler() {
        return compressor;
    }
}
