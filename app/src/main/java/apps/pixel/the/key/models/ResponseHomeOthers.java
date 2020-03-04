package apps.pixel.the.key.models;

public class ResponseHomeOthers {
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "ResponseHomeOthers{" +
                "Message='" + Message + '\'' +
                '}';
    }
}
