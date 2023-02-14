package com.example.solproject.miniproject.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board6 {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private String like;
    private String boardDate;
    private Date boardTime;
    public void getBoardNo(String string) {
    }
}
