package mx.com.dgom.hm.ovhaul.app;


public interface MessageResponseInterface<T> {
    void response(String noInternetError, MessageResponse errorResponse, MessageResponse<T> responseMessage);




}