package com.example.alex.torch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.hardware.camera2.*;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button mButton = null;
//    TextView mText = null;
    boolean flag = false;
    CameraManager mCamreManger = null;
    String[] mList = null;
    String cameraId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.mButton);
//        mText = (TextView) findViewById(R.id.mText);
        mCamreManger = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mList = mCamreManger.getCameraIdList();
            for(String str:mList){
//                mText.append(str+"\n");
                CameraCharacteristics cameraCharacteristics = mCamreManger.getCameraCharacteristics(str);
                if(cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)==true){
                    cameraId= str;
                    break;
//                    mCamreManger.setTorchMode(str,true);
                }else{
//                    mText.append("nonFlash.\n");
                }
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(flag==false){
            try {
                mCamreManger.setTorchMode(cameraId,true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            flag = true;
        }else{
            try {
                mCamreManger.setTorchMode(cameraId,false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            flag = false;
        }
    }
    public void changeTorchMode(){

    }
}
