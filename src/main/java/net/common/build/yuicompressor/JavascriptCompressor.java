/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.common.build.yuicompressor;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import net.common.build.AbstractCompiler;
import org.mozilla.javascript.ErrorReporter;

/**
 *
 * @author Administrator
 */
public class JavascriptCompressor extends AbstractCompiler {
    private final ErrorReporter errorReporter;
    public JavascriptCompressor(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }

    public void compile(Reader in, Writer out) throws IOException {
        JavaScriptCompressor jsCompressor = new JavaScriptCompressor(in, errorReporter);
        jsCompressor.compress(out, -1, true, false, false, false);
    }
    
    @Override
    public void compile(File in, File out) throws IOException {
        JavaScriptCompressor jsCompressor = new JavaScriptCompressor(new FileReader(in), errorReporter);
        FileWriter outputWriter = new FileWriter(out); //note that the filewriter is opened after the file reader

        jsCompressor.compress(outputWriter, -1, true, false, false, false);

        outputWriter.flush();
        outputWriter.close();
    }
}
