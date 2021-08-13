package devops.xiecf.postman.controller;

import devops.xiecf.postman.service.UserService;
import devops.xiecf.postman.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    private List<User> getUserList() {
        return userService.getUserList();
    }

    @ApiImplicitParam(name = "id", value = "用户Id", required = true)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        User user = userService.getUserList().stream().filter(item -> id.equals(item.getId())).findFirst().orElse(null);

        if (logger.isInfoEnabled()) {
            logger.info("用户 {}", user);
        }
        return user;
    }

    @PostMapping("")
    public boolean addUser(@RequestBody User user) {
        List<User> userList = userService.getUserList();
        int id = userList.size() + 1;

        if (user.getName() == null && user.getAge() == null) {
            user = User.builder().id(id).name("xiecf").age(24).build();
        } else {
            user.setId(id);
        }
        userList.add(user);
        return true;
    }

    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (id == null || user == null || !id.equals(user.getId())) return false;
        List<User> userList = userService.getUserList();
        User oldUser = userList.stream().filter(item -> id.equals(item.getId())).findFirst().orElse(null);
        if (oldUser == null)
            return false;
        userList.remove(oldUser);
        userList.add(user);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        if (id == null) return false;
        List<User> userList = userService.getUserList();
        User oldUser = userList.stream().filter(item -> id.equals(item.getId())).findFirst().orElse(null);
        if (oldUser == null)
            return false;
        userList.remove(oldUser);
        return true;
    }

    //todo
    @PostMapping("/import/batch")
    public boolean importUser() {
        return true;
    }
}
