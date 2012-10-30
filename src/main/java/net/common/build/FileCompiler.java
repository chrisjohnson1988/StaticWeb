/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author Chris Johnson
 */
public interface FileCompiler {
    public void compile(File in, File out) throws IOException, CompilationException;
    public void compile(Reader in, Writer out)throws IOException, CompilationException;
}
