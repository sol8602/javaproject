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

import com.example.solproject.miniproject.mapper.BoardMapper1;
import com.example.solproject.miniproject.model.Board1;
import com.example.solproject.miniproject.model.User;

@Controller
@RequestMapping("board1")
public class BoardController1 {
    @Autowired
    BoardMapper1 boardMapper1;

    @GetMapping("boardList") // 게시글 조회
    public String boardList(HttpSession session, Model model){
        ArrayList<Board1> boardList = boardMapper1.boardList();
        model.addAttribute("boardList", boardList);

        return "board1/boardList";
    }
    @GetMapping("boardCreate") // 작성
    public String boardCreate(){

        return "board1/boardCreate";
    }
    @PostMapping("boardCreate") //작성 요청
    public String boardCreate(HttpSession session, Board1 board1){
        User user = (User) session.getAttribute("user");
        board1.setBoardWriter(user.getUserId());
        System.out.println("board1 + :"+board1);
        boardMapper1.boardCreate(board1);
        return "redirect:/board1/boardList";
    }
    @GetMapping("boardDetail") // 상세보기
    public String boardDetail(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board1> boardList = boardMapper1.boardList();
        for(Board1 board1 : boardList){
            if(board1.getBoardNo() == (boardNo)){
                System.out.println(board1);
                model.addAttribute("board1", board1);
            }
        }
        return "board1/boardDetail";
    }

    @GetMapping("boardUpdate")
    public String boardupdate(HttpSession session, Model model, @RequestParam("boardNo") int boardNo){
        ArrayList<Board1> boardList = boardMapper1.boardList();
        for(Board1 board1 : boardList){
            if(board1.getBoardNo() == boardNo){
                model.addAttribute("board1", board1);
            }
        }
    
    return "board1/boardUpdate";
}

    @PostMapping("boardUpdate")
    public String boardUpdate(HttpSession session, Board1 board1){
       boardMapper1.boardUpdate(board1);

        return "redirect:/board1/boardList";
    }

    @GetMapping(value="boardRemove")
    public String boardRemove(@RequestParam("boardNo") int boardNo) {
        boardMapper1.boardRemove(boardNo);
        
        return "redirect:/board1/boardList";
    }
}
