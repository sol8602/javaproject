package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board4;

@Mapper
public interface BoardMapper4 {
    public ArrayList<Board4> boardList();

    public void boardCreate(Board4 board3);

    public void boardUpdate(Board4 board3);

    public void boardRemove(int boardNo);

}
