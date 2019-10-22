package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class DownloadFileDataTO implements Serializable {
    private String file_name;
    private String url;
    private int size;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
