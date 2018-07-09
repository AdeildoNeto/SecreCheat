package pdm.recife.ifpe.edu.br.secrecheat.Domain;

public class Message {
    private String userId;
    private String message;

    public Message() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
