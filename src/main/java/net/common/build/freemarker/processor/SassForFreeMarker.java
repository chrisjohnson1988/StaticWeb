/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.processor;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.common.build.sass.Sass;
import net.common.build.sass.SassException;

/**
 * A Simple Sass processor for freemarker
 * @author Chris Johnson
 */
public class SassForFreeMarker extends AbstractCompilerForFreeMarker {
    private static final Sass COFFEE_SCRIPT_PROCESSOR = new Sass();

    @Override
    public Sass getCompiler() {
        return COFFEE_SCRIPT_PROCESSOR;
    }
}
