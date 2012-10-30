/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Chris Johnson
 */
public abstract class AbstractCompiler implements FileCompiler {

    public void compile(File in, File out) throws IOException, CompilationException {
        FileWriter writer = new FileWriter(out);
        try {
			FileReader reader = new FileReader(in);
			try {
				compile(reader, writer);
			}
			finally {
				reader.close();
			}			
        }
        finally {
            writer.flush();
            writer.close();
        }
    }
}
