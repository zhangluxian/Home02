package cn.edu.sdwu.android02.home.sn170507180109;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {
    private Random random;
    private MyBinder myBinder;
    public MyService(){
    }
    @Override
    public void onCreate() {
        super.onCreate();
        random=new Random();
        myBinder=new MyBinder();
    }
    @Override
    public IBinder onBind(Intent intent) {
        //返回值，会返回给调用者，以后调用者与服务进行交互，都会使用此返回值
        // TODO: Return the communication channel to the service.
        Log.i(MyService.class.toString(),"onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(MyService.class.toString(),"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        //当所有调用者都解绑后，销毁
        Log.i(MyService.class.toString(),"onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder{
        public MyService getRandomService(){
            return MyService.this;
        }
    }

    //实际与调用者进行交付的方法
    public int genRandom(){
        return random.nextInt();
    }
}