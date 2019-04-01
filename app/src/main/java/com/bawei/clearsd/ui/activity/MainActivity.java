package com.bawei.clearsd.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.clearsd.R;
import com.bawei.clearsd.data.bean.ImageBean;
import com.bawei.clearsd.data.utils.DataCleanManagerUtils;
import com.bawei.clearsd.data.utils.PermissionsUtils;
import com.bawei.clearsd.ui.adapter.ImageAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.main_rv)
    RecyclerView mainRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManagerUtils.clearAllCache(MainActivity.this);
                getCacheSize();
            }
        });


        /**
         * 6.0权限
         */
        final String[] permiss = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionsUtils.getInstance().chekPermissions(this, permiss, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {

                //解析数据
                OkGo.<String>get("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/70/1").execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        Gson gson = new Gson();
                        ImageBean imageBean = gson.fromJson(body, ImageBean.class);
                        List<ImageBean.ResultsBean> results = imageBean.getResults();

                        Log.e("gsk", "解析数据"+results);
                        //加载图片
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                        mainRv.setLayoutManager(manager);
                        ImageAdapter imageAdapter = new ImageAdapter(R.layout.gresco_item, results);
                        mainRv.setAdapter(imageAdapter);

                    }
                });
            }

            @Override
            public void forbitPermissons() {

            }
        });










    }


    //获取缓存大小
    private void getCacheSize() {
        try {
            String totalCacheSize = DataCleanManagerUtils.getTotalCacheSize(MainActivity.this);
            textView.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
