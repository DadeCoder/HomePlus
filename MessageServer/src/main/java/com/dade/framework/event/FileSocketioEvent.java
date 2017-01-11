package com.dade.framework.event;

/**
 * socket io文件事件
 * Created by dade on 2016/1/10
 */
public class FileSocketioEvent extends SocketioEvent
{

    private String fileName;    // 文件名称
    private byte[] binaryData;  // 文件数据

    @Override
    public String toString() {
        return "FileSocketioEvent{" +
                "mapping='" + getMapping() + '\'' +
                ", fileName='" + fileName + '\'' +
                ", binaryData size=" + (binaryData == null ? 0 : binaryData.length) +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }
}
