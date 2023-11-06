package satta.win.main.ui;

import android.content.Context;
import android.widget.Toast;

public class MyConstent {
    public static String controll="2";
    public static void setMsg(String messaage, Context  context){
        Toast.makeText(context, messaage, Toast.LENGTH_SHORT).show();

    }
}
