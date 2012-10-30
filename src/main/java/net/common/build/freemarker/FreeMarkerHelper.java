/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker;

import freemarker.cache.TemplateCache;
import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

/**
 *
 * @author Chris Johnson
 */
public class FreeMarkerHelper {
    public static Reader getReaderForTemplateFile(Environment env, String filename, boolean process ) throws IOException, TemplateException {
        String path = getTemplatePathFromRoot(env, filename);
        if(process) {
            Template template = env.getConfiguration().getTemplate(path);
            StringWriter writer = new StringWriter();
            template.process(new HashMap(), writer); //TODO add params
            return new StringReader(writer.toString());
        }
        else {
            TemplateLoader templateLoader = env.getConfiguration().getTemplateLoader();
            Object templateSource = templateLoader.findTemplateSource(path);
            return templateLoader.getReader(templateSource, env.getConfiguration().getDefaultEncoding());
        }
    }
    
    public static String getTemplatePathFromRoot(Environment env, String filename) {
        String rootDir = env.getTemplate().getName();
        rootDir = rootDir.substring(0, rootDir.lastIndexOf('/')+1);
        return TemplateCache.getFullTemplatePath(env, rootDir, filename); 
    }
}
