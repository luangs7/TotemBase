package br.com.luan.totenbase.extras.extras;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import br.com.luan.totenbase.extras.dao.LocalDbImplement;
import br.com.luan.totenbase.extras.model.Search;


/**
 * Created by Luan on 04/05/17.
 */

public class Utils {

    public Utils(View contentView) {
        this.contentView = contentView;
    }
    public Utils() {
    }

    public View contentView;

    public void threadCheck(){
        final Handler h = new Handler();;
        final int delay = 1000;
        //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                checkKeyboardOpen();
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    public void checkKeyboardOpen(){

        this.contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                contentView.getWindowVisibleDisplayFrame(r);
                int screenHeight = contentView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                }
                else {
                    hideSystemUI();
                }
            }
        });
    }

    public void hideSystemUI() {
        this.contentView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public boolean checkEmpty(EditText editText){
        if(editText.getText().length() < 1)
            return true;
        else
            return false;
    }

    public String GsonCustom(List<Object> objects, String key){
        JsonArray ja = new JsonArray();
        for (Object object: objects) {
            Gson gson = new Gson();
            JsonElement je = gson.toJsonTree(object);
            JsonObject jo = new JsonObject();
            jo.add(key, je);
            ja.add(jo);
        }

        JsonObject objMain = new JsonObject();
        objMain.add("surveys",ja);

        return objMain.toString();
    }

    public String GsonCustomAuth(Object object, String key){
        Gson gson = new Gson();
        JsonElement je = gson.toJsonTree(object);
        JsonObject jo = new JsonObject();
        jo.add(key, je);


        return jo.toString();
    }

    public Search getShared(Context context){
        return new LocalDbImplement<Search>(context).getDefault(Search.class);
    }

    public void updateShared(Context context, Search object){
         new LocalDbImplement<Search>(context).clearObject(Search.class);
         new LocalDbImplement<Search>(context).save(object);
    }

    public void clearShared(Context context){
        new LocalDbImplement<Search>(context).clearObject(Search.class);
    }

    public void setShared(Context context, Search object){
        new LocalDbImplement<Search>(context).save(object);
    }
}
