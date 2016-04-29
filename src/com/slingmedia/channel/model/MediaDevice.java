package com.slingmedia.channel.model;

import java.util.List;

/**
 * @author dkalidin
 *
 */
public class MediaDevice {

    public MediaDevice() {

    }
    
    List<Channel> channels;
    
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
}
