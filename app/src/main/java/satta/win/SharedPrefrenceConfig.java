package satta.win;


import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefrenceConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefrenceConfig(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.login_prefrence),Context.MODE_PRIVATE);

    }
    public void writeLoginStats(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_prefrence),status);
        editor.commit();

    }
    public boolean readLoginStatus(){
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_prefrence),false);
        return status;
    }
}

