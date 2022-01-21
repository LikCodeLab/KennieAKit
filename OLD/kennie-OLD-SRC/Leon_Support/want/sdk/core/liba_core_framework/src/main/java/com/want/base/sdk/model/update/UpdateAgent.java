package com.want.base.sdk.model.update;

import android.content.Context;

import java.io.File;

//import com.want.base.sdk.model.update.impl.UmengAgent;

/**
 * <b>Project:</b> apps<br>
 * <b>Create Date:</b> 15/11/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Application update agent.
 * <br>
 */
public class UpdateAgent implements IUpdateable {

    private IUpdateable mUpdateAgent;

    private UpdateAgent(IUpdateable agent) {
        this.mUpdateAgent = agent;
    }

    /**
     * Normal update
     *
     * @param context context
     */
    @Override
    public void update(Context context) {
        mUpdateAgent.update(context);
    }

    /**
     * Silent update
     *
     * @param context context
     */
    @Override
    public void silentUpdate(Context context) {
        mUpdateAgent.silentUpdate(context);
    }

    /**
     * Force update
     *
     * @param context context
     */
    @Override
    public void forceUpdate(Context context) {
        mUpdateAgent.forceUpdate(context);
    }

    /**
     * Install the apk file
     *
     * @param context context
     * @param file    {@link File}
     */
    @Override
    public void install(Context context, File file) {
        mUpdateAgent.install(context, file);
    }

    /**
     * Setup agent config
     *
     * @param builder {@link Builder}
     */
    @Override
    public void setupConfig(Context context, Builder builder) {
        // empty
    }

    public static class Builder {

        private Context context;
        private IUpdateable agent;
        public boolean onlyWithWifi;
        public boolean deltaUpdate;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Config update network
         *
         * @param onlyWithWifi whether only use wifi
         *
         * @return
         */
        public Builder onlyWithWifi(boolean onlyWithWifi) {
            this.onlyWithWifi = onlyWithWifi;
            return this;
        }

        /**
         * Delta update
         *
         * @param deltaUpdate true, deltaUpdate.
         *
         * @return
         */
        public Builder deltaUpdate(boolean deltaUpdate) {
            this.deltaUpdate = deltaUpdate;
            return this;
        }

        /**
         * Agent
         *
         * @param agent
         *
         * @return
         */
        public Builder agent(IUpdateable agent) {
            this.agent = agent;
            return this;
        }

        public UpdateAgent build() {
            if (null == agent) {
                // default use umeng
                //agent = new UmengAgent();
            }
            agent.setupConfig(context, this);
            return new UpdateAgent(agent);
        }
    }


}
