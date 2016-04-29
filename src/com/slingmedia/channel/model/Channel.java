package com.slingmedia.channel.model;

/**
 * @author dkalidin
 *
 */
public class Channel {

    public Channel() {

    }

    private long usid;

    private String svc_name;

    private Boolean hd;
    
    private Boolean audio_only;

    public long getUsid() {
        return usid;
    }

    public void setUsid(long usid) {
        this.usid = usid;
    }

    public String getSvc_name() {
        return svc_name;
    }

    public void setSvc_name(String svc_name) {
        this.svc_name = svc_name;
    }

    public Boolean isHd() {
        return hd;
    }

    public void setHd(Boolean hd) {
        this.hd = hd;
    }

    public String getPadded() {
        return padded;
    }

    public void setPadded(String padded) {
        this.padded = padded;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getChannel_category() {
        return channel_category;
    }

    public void setChannel_category(String channel_category) {
        this.channel_category = channel_category;
    }

    public Boolean isHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

    private String padded;

    private int major;

    private int minor;

    // This could be an ENUM once all categories are known
    private String channel_category;

    private Boolean hide;

    private int channel_number;
    
    private int channel;

    public int getChannel_number() {
        return channel_number;
    }

    public void setChannel_number(int channel_number) {
        this.channel_number = channel_number;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

	public Boolean isAudio_only() {
		return audio_only;
	}

	public void setAudio_only(Boolean audio_only) {
		this.audio_only = audio_only;
	}
}
