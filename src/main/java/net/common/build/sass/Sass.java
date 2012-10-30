/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.sass;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import net.common.build.AbstractCompiler;
import net.common.build.CompilationException;

/**
 *
 * @author Chris Johnson
 */
public class Sass extends AbstractCompiler {
    private final ScriptEngine jruby;
    public Sass() {
        try {
            this.jruby = new ScriptEngineManager().getEngineByName("ruby");
            jruby.eval(
                "require 'rubygems' \n" +
                "require 'sass' \n"
            );
        }
        catch(ScriptException se) {
            throw new Error(se);
        }
    }

    public String compile(String sassToCompile) throws IOException, CompilationException {
        try {
            jruby.put("in", sassToCompile);
            return (String)jruby.eval("Sass.compile($in)");
        } 
        catch (ScriptException ex) {
            throw new SassException("Not all of the required parameters were specified",ex);
        }
    }
    
    public void compile(Reader in, Writer out) throws IOException, CompilationException {
        StringBuilder toProcess = new StringBuilder();
        char[] buf = new char[1024];
        int numRead=0;

        while((numRead=in.read(buf)) != -1){
            toProcess.append(buf, 0, numRead);
        }
        out.write(compile(toProcess.toString()));
    }
}
