package com.zd.concurrent.test;

import java.io.File;

/**
 * @author ruyin_zh
 * @date 2020-06-30
 * @title
 * @description
 */
public class FileSearch implements Runnable {

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()){
            try {
                dictionaryProcess(file);
            }catch (InterruptedException ex){
                System.out.printf("%s : the search has been interrupted.", Thread.currentThread().getName());
            }
        }
    }

    private void dictionaryProcess(File file) throws InterruptedException{
        File[] files = file.listFiles();
        if (files != null){
            int len = files.length;

            for (int i = 0; i < len; i ++){
                if (files[i].isDirectory()){
                    dictionaryProcess(files[i]);
                }else {
                    fileProcess(files[i]);
                }
            }

            if (Thread.interrupted()){
                throw new InterruptedException();
            }
        }
    }

    private void fileProcess(File file) throws InterruptedException{
        if (fileName.equals(file.getName())){
            System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }

        if (Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    public String getInitPath() {
        return initPath;
    }

    public void setInitPath(String initPath) {
        this.initPath = initPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
