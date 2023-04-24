package designpatterns.行为模式.责任链模式.demo1;


import designpatterns.行为模式.责任链模式.demo1.middleware.Middleware;
import designpatterns.行为模式.责任链模式.demo1.middleware.RoleCheckMiddleware;
import designpatterns.行为模式.责任链模式.demo1.middleware.ThrottlingMiddleware;
import designpatterns.行为模式.责任链模式.demo1.middleware.UserExistsMiddleware;
import designpatterns.行为模式.责任链模式.demo1.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public class Demo {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // All checks are linked. Client can build various chains using the same
        // components.
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}