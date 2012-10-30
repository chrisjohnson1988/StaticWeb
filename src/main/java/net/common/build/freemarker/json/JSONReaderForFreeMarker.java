package net.common.build.freemarker.json;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import net.common.build.freemarker.FreeMarkerHelper;
import org.json.JSONException;
import org.json.JSONTokener;

/**
 *
 * @author Chris Johnson
 */
public class JSONReaderForFreeMarker {
    private static final boolean PROCESS_BY_DEFAULT = false;
    private static final JSONObjectWrapper WRAPPER = new JSONObjectWrapper();

    public TemplateModel readFile(String filename) throws TemplateException, IOException, JSONException {
        return readFile(filename, PROCESS_BY_DEFAULT);
    }

    public TemplateModel readURL(String url) throws TemplateException, IOException, JSONException {
        return readURL(new URL(url));
    }
    
    public TemplateModel readFile(String filename, boolean process) throws TemplateException, IOException, JSONException {
        Reader jsonReader = FreeMarkerHelper.getReaderForTemplateFile(Environment.getCurrentEnvironment(), filename, process);
        return readAndClose(jsonReader);
    }
   
    public TemplateModel readURL(URL url) throws TemplateException, IOException, JSONException {
        return readAndClose(new InputStreamReader(url.openStream()));
    }
    
    private TemplateModel readAndClose(Reader in) throws IOException, TemplateModelException, JSONException {
        try {
            return WRAPPER.wrap(new JSONTokener(in).nextValue());
        }
        finally {
            in.close();
        }
    }
}
