package enibdiscovery.enibdiscovery.model;

import android.content.Context;

public class Item extends android.support.v7.widget.AppCompatImageView {
    String name;
    private int id;
    int rotateState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item(Context context, String name){
        super(context);
        this.name=name;
        this.rotateState=0;
    }

    public String getName() {
        return name;
    }

    public int getRotateState() {
        return rotateState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRotateState(int rotateState) {
        this.rotateState = rotateState;
    }

    public void rotate() {
        if (this.getRotateState() == 0) {
            setRotateState(getRotateState() + 1);
        } else {
            setRotateState(getRotateState() - 1);
        }
    }
}

