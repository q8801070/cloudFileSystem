package com.myweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilePath {

    private int id;

    private JSONObject document;
//    private ArrayList<String> image;
//    private ArrayList<String> video;
//    private ArrayList<String> music;
//    private ArrayList<String> others;


}
