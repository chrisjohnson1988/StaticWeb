package net.common.build;

import java.io.File;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Chris Johnson
 */
public class JRubyFactory {
    public static ScriptEngine getJRubyInstance() throws ScriptException {
        ScriptEngine jruby = new ScriptEngineManager().getEngineByName("ruby");
        
        String gemLocation = System.getProperty("gem.path");
        if(gemLocation == null) {
            throw new IllegalArgumentException("No gem.path was specified");
        }  
        
        for(File currGem: new File(new File(gemLocation), "gems").listFiles()) {
            jruby.put("gem", new File(currGem, "lib").getAbsolutePath());
            jruby.eval("$LOAD_PATH.unshift $gem");
        }
        return jruby;
    }
}
