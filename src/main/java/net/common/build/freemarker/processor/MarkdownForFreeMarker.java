/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.processor;

import net.common.build.FileCompiler;
import net.common.build.markdown.MarkdownCompiler;

/**
 * A Simple POJO Reader for processing markdown in freemarker
 * @author Chris Johnson
 */
public class MarkdownForFreeMarker extends AbstractCompilerForFreeMarker {
    private static final MarkdownCompiler MARKDOWN = new MarkdownCompiler();

    @Override
    public FileCompiler getCompiler() {
        return MARKDOWN;
    }
}
