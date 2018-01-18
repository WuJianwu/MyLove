import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

/**
 * function:
 * Created by ex-wujianwu on 2017/9/30.
 */

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    /**
     * apatch 文件
     */
    private static final String APATCH_PATH = "/Dennis.apatch" ;

    @Override
    public void onCreate() {
        super.onCreate();
        PatchManager mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        mPatchManager.loadPatch();

        String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        File apatchPath = new File(patchFileString);
        if (apatchPath.exists()) {
            Log.d(TAG, "补丁文件存在");
            try {
                //添加apatch文件
                mPatchManager.addPatch(patchFileString);
            } catch (IOException e) {
                Log.i(TAG, "打补丁出错了");
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "补丁文件不存在");
        }


    }
}
