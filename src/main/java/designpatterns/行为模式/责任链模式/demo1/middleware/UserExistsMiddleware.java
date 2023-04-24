package designpatterns.行为模式.责任链模式.demo1.middleware;

import designpatterns.行为模式.责任链模式.demo1.server.Server;

/**
 * @description: 检查用户登录信息
 * @author: LuPeng
 * @create: 2022-08-31
 **/

public class UserExistsMiddleware extends Middleware {

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}
