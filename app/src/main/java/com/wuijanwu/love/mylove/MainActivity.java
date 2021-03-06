package com.wuijanwu.love.mylove;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.iflytek.cloud.Setting;
import com.wuijanwu.love.mylove.face.OfflineFaceDemo;
import com.wuijanwu.love.mylove.face.OnlineFaceDemo;
import com.wuijanwu.love.mylove.face.VideoDemo;

/**
 * 人脸识别示例
 */
public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn1 = (Button) findViewById(R.id.btn_online_demo);
		btn1.setOnClickListener(MainActivity.this);

		Button btn2 = (Button) findViewById(R.id.btn_offline_demo);
		btn2.setOnClickListener(MainActivity.this);

		Button btn3 = (Button) findViewById(R.id.btn_video_demo);
		btn3.setOnClickListener(MainActivity.this);

		requestPermissions();
		Setting.setShowLog(true);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_online_demo:
			Toast.makeText(this, "OnlineFaceDemo", Toast.LENGTH_SHORT).show();
			intent = new Intent(MainActivity.this, OnlineFaceDemo.class);
			startActivity(intent);
			break;
		case R.id.btn_offline_demo:
			intent = new Intent(MainActivity.this, OfflineFaceDemo.class);
			startActivity(intent);
			break;
		case R.id.btn_video_demo:
			intent = new Intent(MainActivity.this, VideoDemo.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void requestPermissions() {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				int permission = ActivityCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE);
				if(permission!= PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
							Manifest.permission.LOCATION_HARDWARE,Manifest.permission.READ_PHONE_STATE,
							Manifest.permission.WRITE_SETTINGS,Manifest.permission.READ_EXTERNAL_STORAGE},1122);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
