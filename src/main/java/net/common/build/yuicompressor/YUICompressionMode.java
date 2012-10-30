/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.common.build.yuicompressor;

import net.common.build.FileCompiler;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.mozilla.javascript.ErrorReporter;

/**
 *
 * @author Administrator
 */
public class YUICompressionMode extends EnumeratedAttribute {
    private static final String JAVASCRIPT_MODE = "JS";
    private static final String CSS_MODE = "CSS";

    @Override
    public String[] getValues() {
        return new String[]{JAVASCRIPT_MODE, CSS_MODE};
    }

    public FileCompiler getYuiCompressor(ErrorReporter errorReporter) {
        if(JAVASCRIPT_MODE.equals(this.getValue()))
            return new JavascriptCompressor(errorReporter);
        else if(CSS_MODE.equals(this.getValue()))
            return new CSSCompressor();
        else
            throw new IllegalArgumentException("I do not know the mode " + this.getValue());
    }
}
