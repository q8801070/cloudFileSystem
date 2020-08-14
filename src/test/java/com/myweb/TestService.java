package com.myweb;

import com.myweb.model.AutoSortFileModel;
import com.myweb.service.AutoSortFileService;
import com.myweb.service.FileUploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestService {

    @Autowired
    AutoSortFileService autoSortFileService;

    @Test
    public void test(){

        AutoSortFileModel table = autoSortFileService.getTable(1, 6);
        System.out.println(table);


    }

}











