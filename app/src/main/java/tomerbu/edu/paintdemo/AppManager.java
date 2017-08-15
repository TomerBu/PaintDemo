package tomerbu.edu.paintdemo;

import android.app.Application;
import android.graphics.Path;

/**
 * Created by dev on 9/29/2016.
 */
public class AppManager extends Application {
    static Path p  = new Path();
    @Override
    public void onCreate() {
        super.onCreate();
    }
}