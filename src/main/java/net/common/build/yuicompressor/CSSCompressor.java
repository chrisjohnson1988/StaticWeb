/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.common.build.yuicompressor;

import com.yahoo.platform.yui.compressor.CssCompressor;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import net.common.build.AbstractCompiler;

/**
 *
 * @author Administrator
 */
public class CSSCompressor extends AbstractCompiler {  

    public void compile(Reader in, Writer out) throws IOException {
        CssCompressor cssCompressor = new CssCompressor(in);
        cssCompressor.compress(out, -1);
    }
    
    @Override
    public void compile(File in, File out) throws IOException {
        CssCompressor cssCompressor = new CssCompressor(new FileReader(in));
        FileWriter outputWriter = new FileWriter(out);
        cssCompressor.compress(outputWriter, -1);
        outputWriter.flush();
        outputWriter.close();
    }
}
