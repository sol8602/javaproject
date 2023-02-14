package com.example.solproject.miniproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.solproject.miniproject.mapper.UserMapper;
import com.example.solproject.miniproject.model.User;


// 계정 및 회원을 생성하고 log in 과 log out을 처리하는 Controller 생성
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    

    //회원가입
    @GetMapping("join")
    public String join() {
        return "user/join";

    }
    @PostMapping("join")
    public String join(HttpSession session, User user) {
        userMapper.join(user);

        return "redirect:/";
    }

    //로그인
    @GetMapping("login")
    public String login(){
        return "user/login";
    }


    @PostMapping("login")
    public String login(HttpSession session, User user) {

        String id = user.getUserId();
        String pw = user.getUserPw();
        String getPw = userMapper.getPw(id);

        // 이중 if문을 사용해서 login정보를 비교하고 성공할경우에만 session에 유저 정보를 담아준다
        String view ="";
        if(getPw !=null ){
            if(getPw.equals(pw)){
                User userData = userMapper.selectUser(id);
                session.setAttribute("user", userData);
                view = "redirect:/";
            }else{
                view = "loginFail";
            }
        }else{
            session.setAttribute("user", null);
            view = "loginFail";
        }
        return view;
    }
    //로그아웃
   @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        
        return "redirect:/";
    }
    //탈퇴
    @GetMapping("withdraw")
    public String withdraw(){
        return "/user/withdraw";
    }

    @PostMapping("withdraw")
    public String withdraw(@RequestParam("userId") String userId, User user, HttpSession session){
        User userData = (User)session.getAttribute("user");
        String pw = user.getUserPw();
        String id = user.getUserId();
        if(userData.getUserId().equals(id) && userData.getUserPw().equals(pw)){
            userMapper.withdraw(userId);
            session.setAttribute("user", null);
            return "redirect:/";
        } else {
            return "redirect:/user/withdraw";
        }
    }
}
