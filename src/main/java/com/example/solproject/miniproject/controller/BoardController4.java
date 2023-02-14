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

import com.example.solproject.miniproject.mapper.BoardMapper4;
import com.example.solproject.miniproject.model.Board4;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board4")
public class BoardController4 {
    @Autowired
    BoardMapper4 boardMapper4;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board4> boardList = boardMapper4.boardList();
        model.addAttribute("boardList", boardList);
        
        return "board4/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board4/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board4 board4){
        User user = (User) session.getAttribute("user");
        board4.setBoardWriter(user.getUserId());
        boardMapper4.boardCreate(board4);
        return "redirect:/board4/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board4> boardList = boardMapper4.boardList();
        for(Board4 board4 : boardList){
            if(board4.getBoardNo() == (boardNo)){
                System.out.println(board4);
                model.addAttribute("board4", board4);
            }
        }
        return "board4/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        System.out.println("boardNo -- " + boardNo);
        ArrayList<Board4> boardList = boardMapper4.boardList();
        for(Board4 board4 : boardList){
            if(board4.getBoardNo() == boardNo){
                System.out.println("board4 -- " + board4);
                model.addAttribute("board4", board4);
            }
        }
    
    return "board4/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board4 board4){
        System.out.println("post boardUpdate");
        System.out.println("board4 -- " + board4);
       boardMapper4.boardUpdate(board4);

        return "redirect:/board4/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper4.boardRemove(boardNo);
        
        return "redirect:/board4/boardList";
    }
}
