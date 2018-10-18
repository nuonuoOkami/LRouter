package xzzb.com.runningannotation;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

public class LRouter {


    private static LRouter lRouter;

    /**
     * @author Administrator
     * @time 2018/10/18  10:56
     * @describe 获取传入的Context
     */
    public static Context getContext() {
        return context;
    }



    private static Context context;

    /**
     * @author Administrator
     * @time 2018/10/18  10:56
     * @describe 私有化构造函数
     */
    private LRouter() {

    }

    /**
     * @author Administrator
     * @time 2018/10/18  10:57
     * @describe 初始化
     */
    public static void init(Application application) {
        context = application;
    }

    /**
     * @author Administrator
     * @time 2018/10/18  10:56
     * @describe 单例模式
     */
    public static LRouter getInstance() {
        if (lRouter == null) {
            lRouter = new LRouter();
        }

        return lRouter;
    }

    /**
     * @author Administrator
     * @time 2018/10/18  10:56
     * @describe 拿到生成的路由表 返回一个Postcard对象
     */
    public Postcard build(String path) {
        LRouterMap lRouterMap = new LRouterMap();
        HashMap<String, String> maps = lRouterMap.getMaps();
        Postcard postcard = new Postcard();
        String classNmae = maps.get(path);
        postcard.setPath(classNmae);
        return postcard;

    }


}
