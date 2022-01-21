package com.liba_module_update.update;

import android.content.Context;

import com.liba_module_update.download.DownLoadService;
import com.liba_module_update.download.IDownLoad;
import com.liba_module_update.util.NetWorkUtils;

/**
 * <b>Create Date:</b> 16/9/20<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class UpdateManager {

    private Context mContext;

    private boolean isAutoLoad;

    //必须要子类实现
    private IDownLoad mIDownLoad;

    /**
     * @param context
     * @param mIDownLoad
     * @param is         是不是自动升级 true 自动升级      false 手动升级
     */
    public UpdateManager(Context context, IDownLoad mIDownLoad, boolean is) {
        this.mIDownLoad = mIDownLoad;
        this.isAutoLoad = is;
        this.mContext = context;
    }


    public void inspection() {
        if (NetWorkUtils.isNetworkAvailable(mContext)) {
            DownLoadService.launch(mContext, mIDownLoad.getCodeURL(), isAutoLoad);
        } else {
            //  Toast.makeText(mContext, "网络不可用,请设置网络", Toast.LENGTH_SHORT).show();
        }
    }

//    private boolean isStart() {
//        int oldCode = VersionCode.getVersionCode(mContext);
//        int newCode = mIDownLoad.getNewCode();
//        return newCode > oldCode;
//    }

}
