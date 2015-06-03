package com.djyde.gulliver.utils;

import android.os.Handler;

/**
 * Created by randy on 15/6/3.
 */
public class EasyTimer {
    private Handler handler = new Handler();
    private int past_time;
    private long interval;
    private OnTaskRunListener onTaskRunListener;

    public EasyTimer(){
        this.past_time = 0;
        this.interval = 1000;
    }

    public EasyTimer(long interval){
        this.interval = interval;
    }

    public interface OnTaskRunListener{
        void onTaskRun(long past_time, String rendered_time);
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            past_time += interval;
            handler.postDelayed(this,interval);
            onTaskRunListener.onTaskRun(past_time, time_render(past_time / 1000));
        }
    };

    public void start(){
        handler.removeCallbacks(task);
        handler.postDelayed(task, interval);
    }

    public void stop(){
        handler.removeCallbacks(task);
    }

    public static String time_render(int seconds){
        int mins = seconds / 60;
        int hours = mins / 60;
        int sec = seconds > 60 ? seconds % 60 : seconds;
        String hours_str;
        String seconds_str;
        String mins_str;
        if (hours < 0){
            hours_str = "00";
        } else {
            if (hours < 10){
                hours_str = "0" + hours;
            } else {
                hours_str = String.valueOf(hours);
            }
        }

        if (mins < 0){
            mins_str = "00";
        } else {
            if (mins < 10){
                mins_str = "0" + mins;
            } else {
                mins_str = String.valueOf(mins);
            }
        }

        if (sec < 10){
            seconds_str = "0" + sec;
        } else {
            seconds_str = String.valueOf(sec);
        }

        return hours_str + ":" + mins_str + ":" + seconds_str;
    }

    public static String time_render_chn(int seconds){
        int mins = seconds / 60;
        int hours = mins / 60;
        int sec = seconds > 60 ? seconds % 60 : seconds;
        String hours_str;
        String seconds_str;
        String mins_str;
        if (hours == 0){
            hours_str = "";
        } else {
            if (hours < 10){
                hours_str = hours + " 小时";
            } else {
                hours_str = String.valueOf(hours) + " 小时";
            }
        }

        if (mins == 0){
            mins_str = "";
        } else {
            if (mins < 10){
                mins_str = mins + " 分钟";
            } else {
                mins_str = String.valueOf(mins) + " 分钟";
            }
        }

        if (sec == 10){
            seconds_str = sec + " 秒";
        } else {
            seconds_str = String.valueOf(sec) + " 秒";
        }

        return hours_str + mins_str + seconds_str;
    }

    public void setOnTaskRunListener(OnTaskRunListener onTaskRunListener) {
        this.onTaskRunListener = onTaskRunListener;
    }
}
