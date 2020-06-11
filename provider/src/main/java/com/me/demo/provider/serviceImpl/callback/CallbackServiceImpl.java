package com.me.demo.provider.serviceImpl.callback;

import com.me.demo.api.service.callback.CallbackListener;
import com.me.demo.api.service.callback.CallbackService;
import org.apache.dubbo.config.annotation.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(version = "${dubbo.application.version}")
public class CallbackServiceImpl implements CallbackService {

    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<String, CallbackListener>();

    public CallbackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        for (Map.Entry<String, CallbackListener> entry : listeners.entrySet()) {
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        // 定时触发变更通知
                        Thread.sleep(3000);
                    } catch (Throwable t) {
                        // 防御容错
                        t.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void addListener(String key, CallbackListener listener) {

        System.out.println("service addListener");
        if (listener != null){
            listeners.put(key, listener);

            // 发送变更通知
            listener.changed(getChanged(key));
        }
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
