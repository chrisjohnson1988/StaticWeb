/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.markdown;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import net.common.build.AbstractCompiler;
import net.common.build.CompilationException;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

/**
 *
 * @author Chris Johnson
 */
public class MarkdownCompiler extends AbstractCompiler {
    public static final Markdown MARKDOWN = new Markdown();
    
    public void compile(Reader in, Writer out) throws IOException, CompilationException {
        try {
            MARKDOWN.transform(in, out);
        } catch (ParseException ex) {
            throw new CompilationException("A parse exception occured when processing Markdown.", ex);
        }
    }
    
}
