package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board1;

@Mapper
public interface BoardMapper1 {

    public ArrayList<Board1> boardList();
    
    public void boardCreate(Board1 board1);

    public void boardUpdate(Board1 board1);

    public void boardRemove(int boardNo);

    
    
}
