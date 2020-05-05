package cn.edu.sdwu.android02.home.sn170507180109;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework02);

        //获取作业一按钮
        Button button=(Button) findViewById(R.id.homework_btn1);
        //获取作业二按钮
        Button button2=(Button) findViewById(R.id.homework_btn2);
        //获取作业三按钮
        Button button3=(Button) findViewById(R.id.homework_btn3);
        //获取作业四按钮
        Button button4=(Button) findViewById(R.id.homework_btn4);
        //获取作业五按钮
        Button button5=(Button) findViewById(R.id.homework_btn5);
    }

    //用于实现作业一跳转
    public void startmain(View view){
        //界面跳转
        Intent intent=new Intent(this,homework02_1.class);
        startActivity(intent);
    }
    //用于实现作业二跳转
    public void startmain2(View view){
        Intent intent=new Intent(this,homework02_2.class);
        startActivity(intent);
    }
    /*//用于实现作业三跳转
    public void startmain3(View view){
        Intent intent=new Intent(this,homework3.class);
        startActivity(intent);
    }
    //用于实现作业四跳转
    public void startmain4(View view){
        Intent intent=new Intent(this,homework4.class);
        startActivity(intent);
    }
    //用于实现作业五跳转
    public void startmain5(View view){
        Intent intent=new Intent(this,homework5.class);
        startActivity(intent);
    }*/
}

