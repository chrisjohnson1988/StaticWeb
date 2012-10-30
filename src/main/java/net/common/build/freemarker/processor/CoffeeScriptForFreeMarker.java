/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.processor;

import net.common.build.coffeescript.CoffeeScript;

/**
 *
 * @author Chris Johnson
 */
public class CoffeeScriptForFreeMarker extends AbstractCompilerForFreeMarker {
    private static final CoffeeScript COFFEE_SCRIPT_PROCESSOR = new CoffeeScript();

    @Override
    public CoffeeScript getCompiler() {
        return COFFEE_SCRIPT_PROCESSOR;
    }
}
