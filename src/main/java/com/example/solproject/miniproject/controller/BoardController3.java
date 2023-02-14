package com.example.solproject.miniproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.solproject.miniproject.mapper.BoardMapper3;
import com.example.solproject.miniproject.model.Board3;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board3")
public class BoardController3 {
    @Autowired
    BoardMapper3 boardMapper3;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board3> boardList = boardMapper3.boardList();
        model.addAttribute("boardList", boardList);
        
        return "board3/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board3/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board3 board3){
        User user = (User) session.getAttribute("user");
        board3.setBoardWriter(user.getUserId());
        boardMapper3.boardCreate(board3);
        return "redirect:/board3/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board3> boardList = boardMapper3.boardList();
        for(Board3 board3 : boardList){
            if(board3.getBoardNo() == (boardNo)){
                System.out.println(board3);
                model.addAttribute("board3", board3);
            }
        }
        return "board3/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        System.out.println("boardNo -- " + boardNo);
        ArrayList<Board3> boardList = boardMapper3.boardList();
        for(Board3 board3 : boardList){
            if(board3.getBoardNo() == boardNo){
                System.out.println("board3 -- " + board3);
                model.addAttribute("board3", board3);
            }
        }
    
    return "board3/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board3 board3){
        System.out.println("post boardUpdate");
        System.out.println("board3 -- " + board3);
       boardMapper3.boardUpdate(board3);

        return "redirect:/board3/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper3.boardRemove(boardNo);
        
        return "redirect:/board3/boardList";
    }
}
