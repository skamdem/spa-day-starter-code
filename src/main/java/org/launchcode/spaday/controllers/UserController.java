package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by kamdem
 */
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping(value = {"/{name}", "/"})
    public String displayAllEvents(Model model, @PathVariable(required = false) String name) {
        model.addAttribute("title", "All Users");
        if (name != null) {
            model.addAttribute("name", name);
        }
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping(value = {"/add/{error}", "/add"})
    public String displayAddUserForm(@RequestParam(required = false) String username,
                                     Model model,
                                     @PathVariable(required = false) String error) {
        model.addAttribute("title", "Add User");
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model,
                                     @ModelAttribute User newUser, String verify) {
        if (verify.equals(newUser.getPassword())) {
            UserData.add(newUser);
            return "redirect:" + newUser.getUsername();
        }

        String error = "passwords should match!";
        model.addAttribute("id", newUser.getId());
        model.addAttribute("username", newUser.getUsername());
        model.addAttribute("email", newUser.getEmail());
        model.addAttribute("date", newUser.getDate());
        model.addAttribute("error", error);
        return "redirect:add/" + error;
    }

    @GetMapping("edit/{userId}")
    public String displayEditForm(Model model, @PathVariable int userId) {
        String userName = UserData.getById(userId).getUsername();
        model.addAttribute("title",
                "Edit User '" + userName + "' (id="+userId+")");
        model.addAttribute("theUser", UserData.getById(userId));
        return "user/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int userId, String username, String email) {
        User user = UserData.getById(userId);
        user.setEmail(email);
        user.setUsername(username);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteUserForm(Model model) {
        model.addAttribute("title", "Delete User");
        model.addAttribute("users", UserData.getAll());
        return "user/delete";
    }

    @PostMapping("delete")
    public String processDeleteUserForm(@RequestParam(required = false) int[] userIds) {
        if (userIds != null) {
            for (int id : userIds) {
                UserData.removeById(id);
            }
        }
        return "redirect:";
    }
}

