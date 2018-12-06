package cn.zybwz.smarthomeandroid;

public class ControlDataBean {
    private String roomName;
    private boolean switchStatus;

    public ControlDataBean(String roomName, boolean switchStatus) {
        this.roomName = roomName;
        this.switchStatus = switchStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
    }
}
