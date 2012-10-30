/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.processor;

import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import net.common.build.CompilationException;
import net.common.build.FileCompiler;
import net.common.build.freemarker.FreeMarkerHelper;

/**
 * A simple include object to read any file and include it as a string
 * @author Chris Johnson
 */
public abstract class AbstractCompilerForFreeMarker implements TemplateDirectiveModel {
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        try {
            Reader reader = getReader(env, params, body);
            try {
                getCompiler().compile(reader, env.getOut());
            }
            finally {
                reader.close();
            }
        }
        catch(CompilationException ex) {
            throw new TemplateException("An exception occurred when trying to process template", ex, env);
        }
    }
    
    public static Reader getReader(Environment env, Map params, TemplateDirectiveBody body) throws TemplateException, IOException {
        if(params.containsKey("file")) {
            String filename = params.get("file").toString();
            return FreeMarkerHelper.getReaderForTemplateFile(env, filename, shouldProcess(params, filename));
        }
        else {
            StringWriter writer = new StringWriter();
            body.render(writer);
            return new StringReader(writer.toString());
        }
    }
    
    private static boolean shouldProcess(Map params, String file) {
        return params.containsKey("process") ? (Boolean)params.get("process") : file.endsWith(".ftl");
    }

    public abstract FileCompiler getCompiler();
}
