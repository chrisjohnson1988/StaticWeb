/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/**
 *
 * @author Chris Johnson
 */
public abstract class AbstractBulkFileCompilerTask extends Task {
    private List<FileSet> fileSets;
    
    public AbstractBulkFileCompilerTask() {
        fileSets = new ArrayList<FileSet>();
    }

    public void addFileset(FileSet fileSet) {
        fileSets.add(fileSet);
    }
    
    public @Override void execute() throws BuildException {
        try {
            if(isReady()) {
                for(FileSet currFileSet: fileSets) {
                    DirectoryScanner ds = currFileSet.getDirectoryScanner(getProject());
                    String[] includedFiles = ds.getIncludedFiles();
                    for(int i=0; i<includedFiles.length; i++) {
                        File toCompress = new File(ds.getBasedir(), includedFiles[i].replace('\\','/'));
                        getCompiler().compile(toCompress, getOutputFile(toCompress));
                    }
                }
            }
            else
                throw new BuildException("Not all of the required parameters were specified");
        }
        catch(IOException io) {
            throw new BuildException("An IO Exception occurred", io);
        }
        catch(CompilationException ce) {
            throw new BuildException("An CompilationException occurred", ce);
        }
    }
    
    protected boolean isReady() {
        return true;
    }
     
    protected abstract File getOutputFile(File in);   

    protected abstract FileCompiler getCompiler();
    
}
