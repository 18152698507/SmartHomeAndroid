package cn.zybwz.smarthomeandroid;

/**
 *  购水记录方法类
 */


public class RecordDM {
    private String roomName;
    private String temp;
    private String humidity;
    private String illumination;
    private String co;

    public RecordDM(String roomName, String temp, String humidity, String illumination, String co) {
        this.roomName = roomName;
        this.temp = temp;
        this.humidity = humidity;
        this.illumination = illumination;
        this.co = co;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getIllumination() {
        return illumination;
    }

    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }
}
