package io.macaca.genie.util;

import io.macaca.genie.models.FileModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataUtil {

    public static Map<String, FileModel> map =  new ConcurrentHashMap<>();

    public static Set<String> watchFiles = ConcurrentHashMap.newKeySet();


    public static void addModel(String path, FileModel fileModel) {
        map.put(path, fileModel);
    }
}
