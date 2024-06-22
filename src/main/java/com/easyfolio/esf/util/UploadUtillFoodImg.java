package com.easyfolio.esf.util;

import com.easyfolio.esf.csc.vo.ResImgVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtillFoodImg {

    // 파일 첨부 기능(단일 파일 업로드)
    public static FoodImgVO uploadFile(MultipartFile img) {
        FoodImgVO foodImgVO = null;

        // 첨부파일을 선택했다면
        if (!img.isEmpty()) {
            foodImgVO = new FoodImgVO();
            // 첨부파일
            String originFileName = img.getOriginalFilename();
            // 첨부된 파일명
            String uuid = UUID.randomUUID().toString();
            // 가장 빨리 만나는 자바.jpg
            int dotIndex = originFileName.lastIndexOf(".");
            String extension = originFileName.substring(dotIndex);
            String attachedFileName = uuid + extension;

            // 파일 첨부
            try {
                File file = new File(ConstantVariable.UPLOAD_PATH_FOOD_IMG + attachedFileName);
                img.transferTo(file);

                foodImgVO.setOriginFileName(originFileName);
                foodImgVO.setAttachedFileName(attachedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return foodImgVO;


    }

}
