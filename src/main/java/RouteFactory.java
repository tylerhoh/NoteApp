import Routes.*;
import DTO.NoteObject;

public class RouteFactory {
    public static RouteObject makeRoute(String request, String param) {
        // String[] strArray = request.split(" ",2);
        // String type = strArray[1];

        switch(request) {
            case "/list":
                return new ListRoute();
            case "/delete":
                return new DeleteRoute(param);
            case "/get":
                return new GetRoute(param);
            case "/store":
                return new StoreRoute(param);
            default:
                return new ErrorRoute();
        }
    }

    public static RouteObject makeRoute(String request, String _id, String body) {
        switch (request) {
            case "/update":
                return new UpdateRoute(_id, body);
            default:
                return new ErrorRoute();
        }
    }


}
