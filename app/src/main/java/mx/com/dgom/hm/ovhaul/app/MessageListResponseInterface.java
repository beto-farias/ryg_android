package mx.com.dgom.hm.ovhaul.app;

public interface MessageListResponseInterface<T> {
    void response(String noInternetError, MessageResponse errorResponse, ListResponse<T> responseMessage);

}
