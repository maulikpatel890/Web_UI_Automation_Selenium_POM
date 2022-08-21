package base;

public enum  MessageEnum {
    testMessage("Test is executed successfully successfully");

    private String message;

    MessageEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return  message;
    }
}

