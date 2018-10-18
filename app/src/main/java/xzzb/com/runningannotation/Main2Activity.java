package xzzb.com.runningannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xzzb.com.processor_lib.LRoute;

@LRoute(path = "Main2")
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
