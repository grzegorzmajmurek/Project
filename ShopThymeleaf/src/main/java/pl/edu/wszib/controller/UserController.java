package pl.edu.wszib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.dao.UserDao;
import pl.edu.wszib.domain.User;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("users")
    public String users(Model model) {
        model.addAttribute("users",userDao.getUsers());
        return "users";
    }

    @GetMapping("users/remove/{id}")
    public String remove(@PathVariable long id) {
        userDao.removeUser(id);
        return "redirect:/users";
    }

    @GetMapping("users/new")
    public String newUser(Model model){
        model.addAttribute("user",new User());
        return "user";
    }

    @PostMapping("users/save")
    public String saveUser(User user) {
        userDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        User user = userDao.getById(id);
        model.addAttribute("user",user);
        return "user";
    }
}
