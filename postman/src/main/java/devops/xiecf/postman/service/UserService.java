package devops.xiecf.postman.service;

import devops.xiecf.postman.vo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(User.builder().id(i + 1).name("用户" + (i + 1)).age(18 + i).build());
        }
        return userList;
    }
}
