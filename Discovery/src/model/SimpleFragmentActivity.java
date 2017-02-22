package model;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/13.
 */
public abstract class SimpleFragmentActivity extends Activity {
    public FragmentManager fm;
    private int endTime;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void loadFragment(int containerId,Fragment fragment){
        fm = getFragmentManager();
        fm.beginTransaction().addToBackStack("").add(containerId,fragment).commit();
    }

    public void replaceFragment(int containerId,Fragment fragment){
        fm = getFragmentManager();
        fm.beginTransaction().replace(containerId,fragment).commit();
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            return;
        }

        if (endTime > 0) {
            finish();
        }
        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        endTime = 2000;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                endTime--;
                if (endTime < 0) {
                    endTime = 0;
                    cancel();
                }
            }
        };
        t.schedule(tt, 0, 1);
    }
}
