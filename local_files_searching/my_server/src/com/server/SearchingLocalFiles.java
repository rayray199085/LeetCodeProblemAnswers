package com.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchingLocalFiles {

    public final String PATH = "/Users/stephencao/Downloads";
    private String keyword;
    private List<MyPics> pics;

    public SearchingLocalFiles(String keyword) {
        this.keyword = keyword;
        pics = new ArrayList<>();
    }

    public List<MyPics> find(File[] files, String keyword, String fileType) {
        for (File file : files) {
            if (!file.isDirectory()) {
                String name = file.getName();
                if ((name.contains(keyword)||name.toUpperCase().contains(keyword.toUpperCase())) && name.endsWith(fileType)) {
                    MyPics myPics = new MyPics(file.getAbsolutePath());
                    pics.add(myPics);
                }
            } else {
                find(file.listFiles(), keyword, fileType);
            }
        }
        return pics;
    }
}
