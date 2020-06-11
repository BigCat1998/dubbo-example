package com.me.demo.api.service.callback;

import com.me.demo.api.service.callback.CallbackListener;

import java.io.Serializable;

public class CallbackListenerImpl implements CallbackListener, Serializable {

    private static final long serialVersionUID = 5811971443638510332L;

    @Override
    public void changed(String msg) {
        System.out.println(msg);
    }

}
