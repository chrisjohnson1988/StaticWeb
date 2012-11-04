/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.compass;

import java.io.File;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import net.common.build.JRubyFactory;

/**
 *
 * @author Chris Johnson
 */
public class Compass {
    private final ScriptEngine jruby;
    public Compass() {
        try {
            this.jruby = JRubyFactory.getJRubyInstance();
            jruby.eval(
                "require 'rubygems' \n" +
                "require 'compass' \n" +
                "require 'compass/exec' \n"
            );
        }
        catch(ScriptException se) {
            throw new Error(se);
        }
    }

    public void compile(File compassProjectDir) throws ScriptException {
        jruby.put("in", compassProjectDir.getAbsolutePath());
        jruby.eval("Compass::Exec::SubCommandUI.new(['compile', $in]).run!");
    }
}
