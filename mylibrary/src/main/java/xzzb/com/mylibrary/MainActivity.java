package xzzb.com.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xzzb.com.processor_lib.LRouter;

@LRouter(path = "333")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
