import java.sql.SQLException;
import java.util.Scanner;

import com.uplooking.dao.UsersDao;
import com.uplooking.dao.imp.UsersDaoImp;
import com.uplooking.service.UserService;
import com.uplooking.service.imp.UserServiceImp;
import com.uplooking.vo.Users;

public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		UserService service = new UserServiceImp();
		Users user = new Users();
		System.out.println("请输入用户名:");
		user.setUsername(in.next());
		System.out.println("请输入密码：");
		String password = in.next();
		user.setPassword(password);
		user = service.login(user);
		if (user != null) {
			System.out.println("登录成功，" + user.getName() + "欢迎你!");
		} else {
			System.out.println("登录失败");
		}

	}

}
