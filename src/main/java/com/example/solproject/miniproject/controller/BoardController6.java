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

import com.example.solproject.miniproject.mapper.BoardMapper6;
import com.example.solproject.miniproject.model.Board6;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board6")
public class BoardController6 {
    @Autowired
    BoardMapper6 boardMapper6;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board6> boardList = boardMapper6.boardList();
        model.addAttribute("boardList", boardList);
        
        return "board6/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board6/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board6 board6){
        User user = (User) session.getAttribute("user");
        board6.setBoardWriter(user.getUserId());
        boardMapper6.boardCreate(board6);
        return "redirect:/board6/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board6> boardList = boardMapper6.boardList();
        for(Board6 board6 : boardList){
            if(board6.getBoardNo() == (boardNo)){
                System.out.println(board6);
                model.addAttribute("board6", board6);
            }
        }
        return "board6/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        System.out.println("boardNo -- " + boardNo);
        ArrayList<Board6> boardList = boardMapper6.boardList();
        for(Board6 board6 : boardList){
            if(board6.getBoardNo() == boardNo){
                System.out.println("board6 -- " + board6);
                model.addAttribute("board6", board6);
            }
        }
    
    return "board6/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board6 board6){
        System.out.println("post boardUpdate");
        System.out.println("board6 -- " + board6);
       boardMapper6.boardUpdate(board6);

        return "redirect:/board6/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper6.boardRemove(boardNo);
        
        return "redirect:/board6/boardList";
    }
}
