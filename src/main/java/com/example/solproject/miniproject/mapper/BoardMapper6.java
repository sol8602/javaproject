package com.example.solproject.miniproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.Board6;

@Mapper
public interface BoardMapper6 {
    public ArrayList<Board6> boardList();

    public void boardCreate(Board6 board6);

    public void boardUpdate(Board6 board6);

    public void boardRemove(int boardNo);

}
