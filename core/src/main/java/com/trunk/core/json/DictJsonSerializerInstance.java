package com.trunk.core.json;

import com.trunk.core.annotation.DictJsonAnnotation;

import java.util.HashMap;

/**
 * @author fanhaoming
 * @ClassName DictJsonSerializerInstance
 * @Description TODO
 * @Date 2019/8/12 16:41
 * @Version
 **/
public class DictJsonSerializerInstance {

    private static DictJsonSerializerInstance instance;

    /**
     * Singleton access point to the manager.
     */
    public static DictJsonSerializerInstance getInstance() {
        synchronized (DictJsonSerializerInstance.class) {
            if (instance == null) {
                instance = new DictJsonSerializerInstance();
                instance.init();
            }
        }
        return instance;
    }

    private HashMap<String, DictJsonSerializer> map = null;

    private void init() {
        map = new HashMap<String, DictJsonSerializer>();
    }

    public DictJsonSerializer getByAnnoation(DictJsonAnnotation annotation) {
        if (map.containsKey(annotation.code())) {
            return map.get(annotation.code());
        } else {
            DictJsonSerializer serializer = new DictJsonSerializer();
            serializer.setDictJsonAnnoation(annotation);
            map.put(annotation.code(), serializer);
            return serializer;
        }
    }

}