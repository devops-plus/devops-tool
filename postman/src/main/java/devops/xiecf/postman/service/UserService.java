package devops.xiecf.postman.service;

import devops.xiecf.postman.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static List<User> userList = new ArrayList<>();

    public UserService() {
        for (int i = 0; i < 5; i++) {
            userList.add(User.builder().id(i + 1).name("用户" + (i + 1)).age(18 + i).build());
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
