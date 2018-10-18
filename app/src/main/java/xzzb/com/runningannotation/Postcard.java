package xzzb.com.runningannotation;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

public class Postcard {
    //path
    private String path;
    //要跳转的包名
    private String packageName;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @author Administrator
     * @time 2018/10/18  10:58
     * @describe 跳转
     */
    public void navigation() {
//判断路径是不是null
        if (!TextUtils.isEmpty(path)) {
            //截取包名
            int pos = path.lastIndexOf(".");
            packageName = path.substring(0, pos);
            //根据包名和类名跳转
            Intent intent = new Intent();
            ComponentName componentName = new ComponentName(packageName, path);
            intent.setComponent(componentName);
            LRouter.getContext().startActivity(intent);

        } else {
            //路径为null直接返回
            return;
        }

    }
}
