package com.hhgs.kks.pojo;

public class SourceFile {

    private String path;

    public SourceFile() {
    }

    public SourceFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "SourceFile{" +
                "path='" + path + '\'' +
                '}';
    }
}
