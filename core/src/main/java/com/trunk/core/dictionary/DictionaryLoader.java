package com.trunk.core.dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanhaoming
 * @ClassName DictionaryLoader
 * @Description TODO
 * @Date 2019/8/7 11:24
 * @Version
 **/
public class DictionaryLoader {
    private static DictionaryLoader dictionaryLoader;
    List<DictionaryType> dictionaryTypes = null;
    List<Dictionary> dicts = null;
    List<DictionaryType> enabledDictionaryTypes = null;



    public static DictionaryLoader getInstance(){
        synchronized (DictionaryLoader.class){
            if(dictionaryLoader == null){
                dictionaryLoader = new DictionaryLoader();
                dictionaryLoader.init();
            }
        }
        return dictionaryLoader;
    }

    private void init() {
        dictionaryTypes = new ArrayList<>();
        dicts = new ArrayList<>();
        enabledDictionaryTypes = new ArrayList<>();
        this.loadData();
    }

    private void loadData() {


    }

}
