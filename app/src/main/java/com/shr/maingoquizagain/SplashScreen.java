package com.shr.maingoquizagain;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private final static int EXIT_CODE = 100;

    TextView txtSplashText;
    ImageView imgViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        txtSplashText = findViewById(R.id.textviewLogoText);
        imgViewLogo = findViewById(R.id.imgviewLogo);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition);
//        imgViewLogo.setAnimation(animation);
//        txtSplashText.setAnimation(animation);

        Thread thread = new Thread(() -> {

            try{
                Thread.sleep(3000);
            } catch(Exception e){
                e.printStackTrace();
            }finally{
                GotoPlayActivity();
            }
        });
        thread.start();
    }
    private void GotoPlayActivity(){
        startActivity(new Intent(SplashScreen.this, PlayActivity.class));
    }
    @Override
    protected void onActivityResult(int  requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == EXIT_CODE){
            if(resultCode == RESULT_OK){
                if(data.getBooleanExtra("EXIT", true)){
                    finish();
                }
            }
        }
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("BUGBUG", "onStop() in SplashActivity");
        finish();
    }
}
