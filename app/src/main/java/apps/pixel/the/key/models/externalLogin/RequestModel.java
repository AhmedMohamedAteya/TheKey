package apps.pixel.the.key.models.externalLogin;

public class RequestModel {
    private String Email;

    private String ExternalAccessToken;

    private String Id;

    private String Name;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getExternalAccessToken() {
        return ExternalAccessToken;
    }

    public void setExternalAccessToken(String externalAccessToken) {
        ExternalAccessToken = externalAccessToken;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
