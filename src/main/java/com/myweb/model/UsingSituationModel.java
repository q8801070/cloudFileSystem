package com.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsingSituationModel implements Serializable {

    private int leftSize;
    private int useSize;

    private int fileCount;

    private int documentCount;
    private int imageCount;
    private int videoCount;
    private int musicCount;
    private int otherCount;


}
