package com.halfplatepoha.freetrack.task;

import android.content.Context;
import android.util.Log;

import com.angelhack.freetrack.network.ResponseListener;
import com.angelhack.freetrack.network.TFApi;
import com.angelhack.freetrack.network.TFServiceGenerator;
import com.angelhack.freetrack.network.response.ConsumerLatestEntity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by surajsau on 16/7/16.
 */
public class PullProviderLocationManger {

    private  Context context;
    private long tid;
    private long refreshIntervall = 5000;
    public ResponseListener<ConsumerLatestEntity> delegate;
    Timer timer;
    int delay = 1000; // delay for 1000 mili sec.
    private PullLocationTask task;
    private boolean running = false;
    private TFApi tfAPI;


    private PullProviderLocationManger() {
    }

    public PullProviderLocationManger(Context context){
        this.context=context;
    }



    public void startPullingLocation(ResponseListener<ConsumerLatestEntity> delegate, long id) {

        if (this.timer != null)
            return;

        stopPullingLocation();
        this.delegate = delegate;
        this.tid = id;
        this.timer = new Timer();
        this.task = new PullLocationTask();
        this.timer.scheduleAtFixedRate(task, delay, refreshIntervall);
        running = true;
    }

    public void stopPullingLocation() {

        try {

            if (timer != null) {
                timer.cancel();
                timer.purge();
            }
            if (task != null) {
                task.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;
            task = null;
            timer = null;
        }
    }



    private class PullLocationTask extends TimerTask {

        private PullLocationTask() {

        }

        @Override
        public void run() {
            try {
                if(!running){
                    return;
                }
                tfAPI = TFServiceGenerator.createService(TFApi.class);
                tfAPI.getProviderLatestLocation(tid).enqueue(delegate);
                Log.e("sheetal", "PullLocationTask run ");

            } catch (Exception e) {

                Log.e("", "PullLocationTask Exception ", e);
            }

        }

    }
}
