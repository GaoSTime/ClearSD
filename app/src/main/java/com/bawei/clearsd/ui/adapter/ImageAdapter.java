package com.bawei.clearsd.ui.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.bawei.clearsd.R;
import com.bawei.clearsd.data.bean.ImageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author : GsK
 * e-mail : gaotime0115@gemail.com
 * date   : 2019/3/30 15:39
 * desc   :
 * version: 1.0
 */
public class ImageAdapter extends BaseQuickAdapter<ImageBean.ResultsBean,BaseViewHolder> {
    public ImageAdapter(int layoutResId, @Nullable List<ImageBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean.ResultsBean item) {

        SimpleDraweeView item_frew = helper.getView(R.id.item_frew);
        item_frew.setImageURI(Uri.parse(item.getUrl()));
    }
}
