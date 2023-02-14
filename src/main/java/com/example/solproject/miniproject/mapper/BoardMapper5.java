package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board5;

@Mapper
public interface BoardMapper5 {
    public ArrayList<Board5> boardList();

    public void boardCreate(Board5 board5);

    public void boardUpdate(Board5 board5);

    public void boardRemove(int boardNo);

}
