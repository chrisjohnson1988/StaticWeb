/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.compass;

import java.io.File;
import javax.script.ScriptException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 *
 * @author Chris Johnson
 */
public class CompassTask extends Task {
    private static final Compass COMPASS_EXECUTOR = new Compass();    
    private File in;
    
    public void setIn(File in) {
        this.in = in;
    }

    public @Override void execute() throws BuildException {
        try {
            if(in != null) {
                COMPASS_EXECUTOR.compile(in);
            }
            else
                throw new BuildException("Not all of the required parameters were specified");
        } 
        catch (ScriptException ex) {
            throw new BuildException("It was not possible to execute execute compass.",ex);
        }
    }
}
