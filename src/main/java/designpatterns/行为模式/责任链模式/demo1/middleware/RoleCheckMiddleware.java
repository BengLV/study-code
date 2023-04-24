package designpatterns.行为模式.责任链模式.demo1.middleware;

/**
 * @description: 检查用户角色
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public class RoleCheckMiddleware extends Middleware {

    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }

}
