/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.common.build.freemarker.image.ImageDimensionReaderForFreeMarker;
import net.common.build.freemarker.json.JSONReaderForFreeMarker;
import net.common.build.freemarker.processor.CoffeeScriptForFreeMarker;
import net.common.build.freemarker.processor.MarkdownForFreeMarker;
import net.common.build.freemarker.processor.SassForFreeMarker;
import org.apache.commons.io.FilenameUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/**
 *
 * @author Chris Johnson
 */
public class OutputFreeMarkerTask extends Task {
    private final Configuration cfg;
    private List<FileSet> fileSets;

    public OutputFreeMarkerTask() throws TemplateModelException {
        cfg = new Configuration();
        cfg.setSharedVariable("markdown", new MarkdownForFreeMarker());
        cfg.setSharedVariable("json", new JSONReaderForFreeMarker());
        cfg.setSharedVariable("coffeescript", new CoffeeScriptForFreeMarker());
        cfg.setSharedVariable("sass", new SassForFreeMarker());
        cfg.setSharedVariable("image", new ImageDimensionReaderForFreeMarker());
        fileSets = new ArrayList<FileSet>();
    }
    
    
    public void addFileset(FileSet fileSet) {
        fileSets.add(fileSet);
    }
    
    public @Override void execute() throws BuildException {
        try {
            for(FileSet currFileSet: fileSets) {
                DirectoryScanner ds = currFileSet.getDirectoryScanner(getProject());
                cfg.setDirectoryForTemplateLoading(ds.getBasedir());
                String[] includedFiles = ds.getIncludedFiles();
                for(int i=0; i<includedFiles.length; i++) {
                    String inputFileName = includedFiles[i].replace('\\','/');
                    compile(inputFileName, getOutputFile(ds.getBasedir(),inputFileName));
                }
            }
        }
        catch(IOException io) {
            throw new BuildException("An IO Exception occurred" + io.getMessage(), io);
        }
    }
        
    public void compile(String template, File out) throws BuildException {
        try {
            FileWriter outputWriter = new FileWriter(out);
            try {
                cfg.getTemplate(template).process(new HashMap(), outputWriter);
            } finally {
                outputWriter.flush();
                outputWriter.close();
            }
        } catch (IOException ex) {
            throw new BuildException("An IOException occured whilst processing " + template + "\n" + ex.getMessage(), ex);
        }
        catch(TemplateException te) {
            throw new BuildException("A template exception occured during build" +  "\n" + te.getMessage(), te);
        }
    }
    
    private static File getOutputFile(File parent, String inputName) {
        return new File(parent, FilenameUtils.removeExtension(inputName));
    }
}
