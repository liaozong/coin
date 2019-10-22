package com.yijiu.newcoin.activity.wallet;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.yijiu.newcoin.R;
import com.yijiu.newcoin.base.BaseAty;
import com.yijiu.newcoin.databinding.ActivityRechargeBinding;

public class RechargeAty extends BaseAty {

    private ActivityRechargeBinding mBinding;

    @Override
    protected void loadingData(String requestType) {

    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void initView() {
        super.initView();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recharge);
    }

    @Override
    protected void initData() {
        super.initData();
        ImageView imageView = mBinding.inTitle.findViewById(R.id.iv_imageView);
        imageView.setImageResource(R.mipmap.dilirecharge);

      /*  RequestOptions options = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(true) // 不使用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                ;
        Glide.with(this).load(RetrofitUtils.ImgUrl + "").apply(options).into(mBinding.ivTwoCode);
        final Bitmap bitmap = ZXingUtils.creatBarcode(this, "", 230, 75, false);
//            final Bitmap mBitmap = CodeUtils.createImage(requestType, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.integral));
            final Bitmap mBitmap = CodeUtils.createImage("", 400, 400,null);

            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    mBinding.ivTwoCode.setImageBitmap(mBitmap);
                }
            });
*/
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mBinding.inTitle.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.rlSavePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
