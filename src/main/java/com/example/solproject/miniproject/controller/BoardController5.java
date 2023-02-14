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

import com.example.solproject.miniproject.mapper.BoardMapper5;
import com.example.solproject.miniproject.model.Board5;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board5")
public class BoardController5 {
    @Autowired
    BoardMapper5 boardMapper5;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board5> boardList = boardMapper5.boardList();
        model.addAttribute("boardList", boardList);
        
        return "board5/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board5/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board5 board5){
        User user = (User) session.getAttribute("user");
        board5.setBoardWriter(user.getUserId());
        boardMapper5.boardCreate(board5);
        return "redirect:/board5/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board5> boardList = boardMapper5.boardList();
        for(Board5 board5 : boardList){
            if(board5.getBoardNo() == (boardNo)){
                System.out.println(board5);
                model.addAttribute("board5", board5);
            }
        }
        return "board5/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        System.out.println("boardNo -- " + boardNo);
        ArrayList<Board5> boardList = boardMapper5.boardList();
        for(Board5 board5 : boardList){
            if(board5.getBoardNo() == boardNo){
                System.out.println("board5 -- " + board5);
                model.addAttribute("board5", board5);
            }
        }
    
    return "board5/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board5 board5){
        System.out.println("post boardUpdate");
        System.out.println("board5 -- " + board5);
       boardMapper5.boardUpdate(board5);

        return "redirect:/board5/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper5.boardRemove(boardNo);
        
        return "redirect:/board5/boardList";
    }
}
