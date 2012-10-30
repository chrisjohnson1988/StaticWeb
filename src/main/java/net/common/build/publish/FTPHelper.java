/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.publish;


import java.io.*;
import java.util.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Chris Johnson
 */
class FTPHelper {
    public static void cleanOutFiles(FTPClient client) throws IOException {
        for(FTPFile curr : client.listFiles())
           deleteFile(client, curr);
    }
 
    public static void deleteFile(FTPClient client, FTPFile toDelete) throws IOException {
        if(!(toDelete.getName().equals(".") || toDelete.getName().equals(".."))) {
            if(toDelete.isDirectory()) {
                client.changeWorkingDirectory(toDelete.getName());
                FTPFile[] filesToDelete = client.listFiles();
                for(FTPFile currFileToDelete: filesToDelete)
                    deleteFile(client, currFileToDelete);
                client.changeToParentDirectory();
                client.removeDirectory(toDelete.getName());
            }
            else
                client.deleteFile(toDelete.getName());
        }
    }
    
    /**
     * Upload a file or a folder using the recursion method
     */
    public static void uploadFile(FTPClient client, File toUpload) throws IOException {
        uploadFileRecursion(client, toUpload, null);
    }
    
    public static void uploadContentsOfDirectory(FTPClient client, File baseDir, List<File> toInclude) throws IOException {
        for(File currFile : baseDir.listFiles())
            uploadFileRecursion(client, currFile, toInclude); //get all the files in the directory and then upload them 
    }
    
    private static void uploadFileRecursion(FTPClient client, File toUpload, List<File> toInclude) throws IOException {
        if(toInclude==null || toInclude.contains(toUpload)) {
            if(toUpload.isDirectory()) {
                client.makeDirectory(toUpload.getName());
                client.changeWorkingDirectory(toUpload.getName());
                for(File currFile : toUpload.listFiles())
                    uploadFileRecursion(client, currFile, toInclude);
                client.changeToParentDirectory();
            }
            else 
                uploadStreamAndClose(client, toUpload.getName(), new FileInputStream(toUpload));
        }
    }
    
    private static boolean uploadStreamAndClose(FTPClient client, String name, InputStream in) throws IOException {
        try {
            return client.storeFile(name, in);
        }
        finally {
            in.close();
        }
    }
}
