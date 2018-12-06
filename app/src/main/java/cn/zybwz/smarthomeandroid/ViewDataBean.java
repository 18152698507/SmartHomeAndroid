package cn.zybwz.smarthomeandroid;

public class ViewDataBean {
    private String arg;
    private String value;
    private String message;
    private int code;

    public ViewDataBean(String arg, String value, int code,String message) {
        this.arg = arg;
        this.value = value;
        this.message = message;
        this.code = code;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
