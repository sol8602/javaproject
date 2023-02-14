package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board2;

@Mapper
public interface BoardMapper2 {
    public ArrayList<Board2> boardList();

    public void boardCreate(Board2 board2);

    public void boardUpdate(Board2 board2);

    public void boardRemove(int boardNo);

}
