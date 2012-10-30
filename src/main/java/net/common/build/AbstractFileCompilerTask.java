/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 *
 * @author Chris Johnson
 */
public abstract class AbstractFileCompilerTask extends Task {
    private File in, out;
    
    public void setIn(File in) {
        this.in = in;
    }

    public void setOut(File out) {
        this.out = out;
    }

    public @Override void execute() throws BuildException {
        try {
            if(in != null && out != null) {
                getCompiler().compile(in, out);  
            }
            else
                throw new BuildException("Not all of the required parameters were specified");
        }
        catch(CompilationException ce) {
            throw new BuildException("An CompilationException occured whilst trying to compile sass", ce);
        }
        catch(IOException io) {
            throw new BuildException("An IOException occured whilst trying to compile sass", io);
        }
    }
    
    protected abstract FileCompiler getCompiler();
}
