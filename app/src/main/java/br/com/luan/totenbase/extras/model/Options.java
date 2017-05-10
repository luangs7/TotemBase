package br.com.luan.totenbase.extras.model;

/**
 * Created by Luan on 05/05/17.
 */

public class Options {
    private String title;
    private int resource;
    private boolean actived;


    public Options(String title, int resource) {
        this.title = title;
        this.resource = resource;
    }

    public Options() {
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
