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

import com.example.solproject.miniproject.mapper.BoardMapper2;
import com.example.solproject.miniproject.model.Board2;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board2")
public class BoardController2 {
    @Autowired
    BoardMapper2 boardMapper2;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board2> boardList = boardMapper2.boardList();
        model.addAttribute("boardList", boardList);
        
        return "board2/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board2/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board2 board2){
        User user = (User) session.getAttribute("user");
        board2.setBoardWriter(user.getUserId());
        boardMapper2.boardCreate(board2);
        return "redirect:/board2/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board2> boardList = boardMapper2.boardList();
        for(Board2 board2 : boardList){
            if(board2.getBoardNo() == (boardNo)){
                System.out.println(board2);
                model.addAttribute("board2", board2);
            }
        }
        return "board2/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        System.out.println("boardNo -- " + boardNo);
        ArrayList<Board2> boardList = boardMapper2.boardList();
        for(Board2 board2 : boardList){
            if(board2.getBoardNo() == boardNo){
                System.out.println("board2 -- " + board2);
                model.addAttribute("board2", board2);
            }
        }
    
    return "board2/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board2 board2){
        System.out.println("post boardUpdate");
        System.out.println("board2 -- " + board2);
       boardMapper2.boardUpdate(board2);

        return "redirect:/board2/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper2.boardRemove(boardNo);
        
        return "redirect:/board2/boardList";
    }
}
