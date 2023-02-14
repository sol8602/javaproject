package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board3;

@Mapper
public interface BoardMapper3 {
    public ArrayList<Board3> boardList();

    public void boardCreate(Board3 board3);

    public void boardUpdate(Board3 board3);

    public void boardRemove(int boardNo);

}
