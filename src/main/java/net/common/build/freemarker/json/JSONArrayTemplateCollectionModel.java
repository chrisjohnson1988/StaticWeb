/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.common.build.freemarker.json;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateSequenceModel;
import net.common.build.freemarker.WrappedTemplateMethod;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Chris Johnson
 */
public class JSONArrayTemplateCollectionModel extends WrappedTemplateMethod<JSONArray> implements TemplateCollectionModel, TemplateSequenceModel {
    private JSONArray jsonArray;
    private final JSONObjectWrapper wrapper;

    public JSONArrayTemplateCollectionModel(JSONArray jsonArray, JSONObjectWrapper wrapper) {
        super(jsonArray);
        this.jsonArray = jsonArray;
        this.wrapper = wrapper;
    }

    @Override
    public TemplateModelIterator iterator() throws TemplateModelException {
        return new JSONArrayTemplateModelIterator();
    }

    @Override
    public TemplateModel get(int i) throws TemplateModelException {
        try {
            return wrapper.wrap(jsonArray.get(i));
        } catch (JSONException jsonEx) {
            throw new TemplateModelException("A JSON Exception occurred", jsonEx);
        }
    }

    @Override
    public int size() throws TemplateModelException {
        return jsonArray.length();
    }
    
    private class JSONArrayTemplateModelIterator implements TemplateModelIterator {
        private int i = 0;
        
        @Override
        public TemplateModel next() throws TemplateModelException {
            return get(i++);
        }

        @Override
        public boolean hasNext() throws TemplateModelException {
            return i < jsonArray.length();
        }
    }
}
