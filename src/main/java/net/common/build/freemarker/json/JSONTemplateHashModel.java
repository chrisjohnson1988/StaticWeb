/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.json;

import freemarker.template.SimpleCollection;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import net.common.build.freemarker.WrappedTemplateMethod;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Chris Johnson
 */
public class JSONTemplateHashModel extends WrappedTemplateMethod<JSONObject> implements TemplateHashModelEx {
    private final JSONObject toWrap;
    private final JSONObjectWrapper wrapper;

    public JSONTemplateHashModel(JSONObject toWrap, JSONObjectWrapper wrapper) {
        super(toWrap);
        this.toWrap = toWrap;
        this.wrapper = wrapper;
    }

    @Override
    public int size() throws TemplateModelException {
        return toWrap.length();
    }

    @Override
    public TemplateCollectionModel keys() throws TemplateModelException {
        return new JSONArrayTemplateCollectionModel(toWrap.names(), wrapper);
    }

    @Override
    public TemplateCollectionModel values() throws TemplateModelException {
        return new SimpleCollection(toWrap.keys(), wrapper);
    }

    @Override
    public TemplateModel get(String string) throws TemplateModelException {
        try {
            return wrapper.wrap(toWrap.get(string));
        } catch (JSONException jsonEx) {
            throw new TemplateModelException("A JSON Exception occurred", jsonEx);
        }
    }

    @Override
    public boolean isEmpty() throws TemplateModelException {
        return toWrap.length() != 0;
    }
}
