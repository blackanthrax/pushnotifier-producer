package com.github.blackanthrax.pushnotifier.producer.messaging;

import java.util.ArrayList;
import java.util.List;

public class DeviceList {

    private String status;
    private Integer code;
    private List<Device> devices = new ArrayList<Device>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}
