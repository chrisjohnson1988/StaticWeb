/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.publish;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/**
 *
 * @author Chris Johnson
 */
public class PublishTask extends Task {
    private boolean cleanOutFTP;
    private String userid, password, server;
    private List<FileSet> fileSets;
    
    public PublishTask() {
        fileSets = new ArrayList<FileSet>();
    }
    
    @Override
    public void execute() throws BuildException {
        try {
            FTPClient f = new FTPClient();
            f.connect(server);
            f.login(userid, password);
            f.setFileType(FTP.BINARY_FILE_TYPE);

            if(cleanOutFTP) {
                log("Cleaning up the FTP");
                FTPHelper.cleanOutFiles(f); //remove all the files if requested
            }
            
            log("Uploading basic web files and folders");
            uploadFileSets(f); //upload all the filesets
            
            f.logout(); //all done, I can now finish
        } 
        catch (Exception ex) {
            throw new BuildException(ex);
        }
    }
    
    private void uploadFileSets(FTPClient f) throws IOException {
        List<File> toSave = new ArrayList<File>();
        for(FileSet currFileSet : fileSets) {
            DirectoryScanner ds = currFileSet.getDirectoryScanner(getProject());
            addFilesToList(toSave, currFileSet, ds.getIncludedDirectories());
            addFilesToList(toSave, currFileSet, ds.getIncludedFiles());
            FTPHelper.uploadContentsOfDirectory(f, currFileSet.getDir(), toSave);
        }
    }
    
    private void addFilesToList(List<File> toAddTo, FileSet context, String[] filesList) {
        for(String currFile : filesList)
            toAddTo.add(new File(context.getDir(), currFile));
    }
    
    public void setCleanout(boolean cleanOutFTP) {
        this.cleanOutFTP = cleanOutFTP;
    }
    
    public void addFileset(FileSet filesetToAdd) {
        fileSets.add(filesetToAdd);
    }
    
    public void setServer(String server) {
        this.server = server;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
