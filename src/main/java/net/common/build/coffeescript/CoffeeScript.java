/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.coffeescript;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import net.common.build.AbstractCompiler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;

/**
 *
 * @author Chris Johnson
 */
public class CoffeeScript extends AbstractCompiler {

    private final Scriptable globalScope;

    public CoffeeScript() {
        InputStream inputStream = getClass().getResourceAsStream("coffee-script.js");
        try {
            try {
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                try {
                    Context context = Context.enter();
                    context.setOptimizationLevel(-1); // Without this, Rhino hits a 64K bytecode limit and fails
                    try {
                        globalScope = context.initStandardObjects();
                        context.evaluateReader(globalScope, reader, "coffee-script.js", 0, null);
                    } finally {
                        Context.exit();
                    }
                } finally {
                    reader.close();
                }
            } catch (UnsupportedEncodingException e) {
                throw new Error(e); // This should never happen
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new Error(e); // This should never happen
        }

    }

    public String compile(String coffeeScriptSource) throws JCoffeeScriptCompileException {
        Context context = Context.enter();
        try {
            Scriptable compileScope = context.newObject(globalScope);
            compileScope.setParentScope(globalScope);
            compileScope.put("coffeeScriptSource", compileScope, coffeeScriptSource);
            try {
                return StringEscapeUtils.unescapeEcmaScript((String) context.evaluateString(compileScope, "CoffeeScript.compile(coffeeScriptSource);", "JCoffeeScriptCompiler", 0, null));
            } catch (JavaScriptException e) {
                throw new JCoffeeScriptCompileException(e);
            }
        } finally {
            Context.exit();
        }
    }
    
    public void compile(Reader in, Writer out) throws IOException, JCoffeeScriptCompileException {
        StringBuilder toProcess = new StringBuilder();
        char[] buf = new char[1024];
        int numRead=0;

        while((numRead=in.read(buf)) != -1){
            toProcess.append(buf, 0, numRead);
        }
        out.write(compile(toProcess.toString()));
    }
}
