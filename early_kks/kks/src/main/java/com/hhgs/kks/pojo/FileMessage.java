package com.hhgs.kks.pojo;

import java.util.List;

public class FileMessage {

    private String outputPath;
    private String scheduler_name;
    private List<SourceFile> inputPathList;

    public FileMessage(String outputPath, String scheduler_name, List<SourceFile> inputPathList) {
        this.outputPath = outputPath;
        this.scheduler_name = scheduler_name;
        this.inputPathList = inputPathList;
    }

    public FileMessage() {
    }

    public List<SourceFile> getInputPathList() {
        return inputPathList;
    }

    public void setInputPathList(List<SourceFile> inputPathList) {
        this.inputPathList = inputPathList;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getScheduler_name() {
        return scheduler_name;
    }

    public void setScheduler_name(String scheduler_name) {
        this.scheduler_name = scheduler_name;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "outputPath='" + outputPath + '\'' +
                ", scheduler_name='" + scheduler_name + '\'' +
                ", inputPathList=" + inputPathList +
                '}';
    }
}
