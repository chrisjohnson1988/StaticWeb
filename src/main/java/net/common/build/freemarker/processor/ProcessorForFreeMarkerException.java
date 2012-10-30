/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.processor;

/**
 *
 * @author Chris Johnson
 */
public class ProcessorForFreeMarkerException extends Exception {

    ProcessorForFreeMarkerException(Throwable cause) {
        super(cause);
    }

    ProcessorForFreeMarkerException(String mess, Throwable cause) {
        super(mess, cause);
    }
    
}
