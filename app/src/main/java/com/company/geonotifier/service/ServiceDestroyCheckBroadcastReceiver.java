package com.company.geonotifier.service;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.company.geonotifier.repository.ReminderRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceDestroyCheckBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ReminderRepository repository = new ReminderRepository((Application) context.getApplicationContext());

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (repository.getActiveReminderCount() > 0) {
                Intent serviceIntent = new Intent(context, ReminderService.class);
                ContextCompat.startForegroundService(context, serviceIntent);
            }
        });
    }
}
