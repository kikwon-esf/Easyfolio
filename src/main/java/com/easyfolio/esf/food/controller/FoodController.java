package com.easyfolio.esf.food.controller;

import com.easyfolio.esf.csc.vo.PageVO;
import com.easyfolio.esf.csc.vo.inq.InqImgVO;
import com.easyfolio.esf.csc.vo.qna.QnaVO;
import com.easyfolio.esf.food.service.FoodService;
import com.easyfolio.esf.food.vo.FoodImgVO;
import com.easyfolio.esf.food.vo.FoodStepsVO;
import com.easyfolio.esf.food.vo.FoodVO;
import com.easyfolio.esf.member.service.MemberService;
import com.easyfolio.esf.member.vo.AlarmVO;
import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.myPage.service.MyPageService;
import com.easyfolio.esf.myPage.vo.CommentVO;
import com.easyfolio.esf.myPage.vo.FavoriteVO;
import com.easyfolio.esf.util.UploadUtillFoodImg;
import com.easyfolio.esf.util.UploadUtillInq;
import com.easyfolio.esf.weather.service.WeatherService;
import com.easyfolio.esf.weather.vo.DdabongVO;
import com.easyfolio.esf.weather.vo.RegionVO;
import com.easyfolio.esf.weather.vo.WeatherVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.groovy.parser.antlr4.SyntaxErrorReportable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;
    //일단은 myPageService추가, 논의 후 foodService에 주입
    private final MyPageService myPageService;
    private final MemberService memberService;
    private final WeatherService weatherService;

    @RequestMapping(value = "/searchFoodPage", method = {RequestMethod.GET/*, RequestMethod.POST*/})
    public String searchFoodAllPage(Model model, FoodVO foodVO,
                                    Principal principal, MemberVO memberVO) throws Exception {

        if (RequestMethod.POST.toString().equals(RequestContextHolder.currentRequestAttributes().getAttribute("method", RequestAttributes.SCOPE_REQUEST))) {
            setupFavoriteList(model, principal, memberVO);
        }
        foodVO.setTotalDataCnt(foodService.searchFoodCnt(foodVO));
        foodVO.setPageInfo();
        model.addAttribute("nowPage", foodVO.getNowPage());

        setupFoodList(model, foodVO);
        setupSearchDetails(model, foodVO);

        if(principal != null){
            model.addAttribute("recentViewList",FoodController.setCommentCnt(foodService.selectRecentView(principal.getName()),myPageService));
        }

        return "/content/food/food_search";
    }

    private void setupFavoriteList(Model model, Principal principal, MemberVO memberVO) throws JsonProcessingException {
        if (principal != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            memberVO.setMemberId(principal.getName());
            List<String> favoriteList = myPageService.getFavoriteListString(memberVO);
            model.addAttribute("favoriteList", objectMapper.writeValueAsString(favoriteList));
        }
    }

    private void setupFoodList(Model model, FoodVO foodVO) {
        List<FoodVO> foodList = setCommentCnt(foodService.searchFoodAll(foodVO), myPageService);
        model.addAttribute("foodList", foodList);
    }
    public static List<FoodVO> setCommentCnt(List<FoodVO> list, MyPageService myPageService){
        for(int i = 0 ; i < list.size() ; i++){
            FoodVO foodEach = list.get(i) ;
            String foodCode = foodEach.getFoodCode();
            int cnt = myPageService.commentListCnt(new CommentVO().withFoodCode(foodCode));
            foodEach.setFoodCommentCnt(cnt);
        }
        return list;
    }

    private void setupSearchDetails(Model model, FoodVO foodVO) {
        model.addAttribute("searchFoodValue", foodVO.getSearchFoodValue());
        model.addAttribute("searchFoodCnt", foodService.searchFoodCnt(foodVO));
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodKindCode", foodVO.getFoodKindCode());
        if (foodVO.getFoodKindCode() != null) {
            model.addAttribute("foodKind", foodService.foodKindText(foodVO));
        }
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodUsageCode", foodVO.getFoodUsageCode());
        if (foodVO.getFoodUsageCode() != null) {
            model.addAttribute("foodUsage", foodService.foodUsageText(foodVO));
        }
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodMtrlCode", foodVO.getFoodMtrlCode());
        if (foodVO.getFoodMtrlCode() != null) {
            model.addAttribute("foodMtrl", foodService.foodMtrlText(foodVO));
        }
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        model.addAttribute("foodTypeCode", foodVO.getFoodTypeCode());
        if (foodVO.getFoodTypeCode() != null) {
            model.addAttribute("foodType", foodService.foodTypeText(foodVO));
        }
    }


    @GetMapping(value = "detail")
    public String foodDtl(Principal principal ,@ModelAttribute("foodCode") String foodCode,Model model, FoodVO foodVO,@RequestParam(value = "foodCommentId", required = false) String foodCommentId ) {
        if(foodCode.equals("") || foodCode != null){
            foodVO.setFoodCode(foodCode);
        }

        if(principal != null){
            foodVO.setMemberId(principal.getName());
            foodService.insertRecentView(foodVO);

            model.addAttribute("recentViewList", FoodController.setCommentCnt(foodService.selectRecentView(principal.getName()),myPageService));
        }

        foodService.updateFoodInqCnt(foodVO);
        FoodVO detailFoodVO = foodService.getFoodDtl(foodVO);
        model.addAttribute("foodDetail", detailFoodVO);
        model.addAttribute("foodCodeList", foodService.selectFoodCode(detailFoodVO));
        setupSearchDetails(model, foodVO);
        String mtrl = detailFoodVO.getFoodMtrlCn();
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]([^\\[]+)(?=\\[|$)");
        Matcher matcher = pattern.matcher(mtrl);

        List<String> mtrlTitle = new ArrayList<>();
        List<List<String>> mtrlMt1 = new ArrayList<>();
        List<List<String>> mtrlMt2 = new ArrayList<>();

        while (matcher.find()) {
            mtrlTitle.add(matcher.group(1).trim());
            String materials = matcher.group(2).trim();
            String[] mtrls = materials.split("\\|");
            for (int i = 0; i < mtrls.length; i++) {
                String[] splitMtrls = mtrls[i].trim().split("\\s+(?=[^\\s]*$)");
                List<String> mtrlList = Arrays.asList(splitMtrls);
                if (mtrlTitle.size() == 1) {
                    mtrlMt1.add(mtrlList);
                } else {
                    mtrlMt2.add(mtrlList);
                }
            }
        }

        // 결과 출력
        model.addAttribute("foodImg", foodService.selectFoodImg(foodVO));
        model.addAttribute("mtrlTitles", mtrlTitle);
        model.addAttribute("mtrlMt1", mtrlMt1);
        if (mtrlMt2.isEmpty()) {
            mtrlMt2.add(new ArrayList<>());
        }
        model.addAttribute("mtrlMt2", mtrlMt2);
        model.addAttribute("foodCommentId", foodCommentId);
        if(foodService.getFoodSteps(foodVO) != null && foodService.getFoodSteps(foodVO).size() > 0){
            String foodStepsStr = String.valueOf(foodService.getFoodSteps(foodVO).get(0).getFoodEx());
            List<String> foodStepsList = Arrays.asList(foodStepsStr.split("%"));
            model.addAttribute("foodSteps", foodStepsList);
        }else{
            model.addAttribute("foodSteps",foodService.getFoodSteps(foodVO));
        }
        if(principal != null){
            model.addAttribute("memberIdCheck", principal.getName());
        }


        return "/content/food/food_detail";
    }
    //foodDetail에서 댓글 읽어오기
    @GetMapping(value = "/comment")
    public String getCommentList(Model model, CommentVO commentVO){
        Map<String,CommentVO> commentMap = myPageService.getCommentVOList(commentVO);
        List<CommentVO> reCommentList = myPageService.getReComment(commentVO);

//        commentVO.setTotalDataCnt(myPageService.commentListCnt(commentVO));
        List<CommentVO> commentList = CommentVO.sortReComment(commentMap, reCommentList);
        model.addAttribute("commentList", commentList);
        model.addAttribute("inputComment",new CommentVO()
                .withReciveMemberId(
                        commentVO.getReciveMemberId()
                )
        );
//        model.addAttribute("nowPage", commentVO.getNowPage());
        model.addAttribute("totalCount",myPageService.commentListCnt(commentVO));
        model.addAttribute("foodCode", commentVO.getFoodCode());

        return "content/myPage/replace/food_comment";
    }
    //댓글 더 보여줘!!
    @GetMapping(value = "/moreComment")
    public String getMoreCommentList(Model model, CommentVO commentVO){
        System.err.println("??");
        System.err.println(commentVO.getNowPage());
        Map<String,CommentVO> commentMap = myPageService.getCommentVOList(commentVO);
        List<CommentVO> reCommentList = myPageService.getReComment(commentVO);

        List<CommentVO> commentList = CommentVO.sortReComment(commentMap, reCommentList);
        model.addAttribute("commentList", commentList);
        model.addAttribute("inputComment",new CommentVO()
                .withReciveMemberId(
                        commentVO.getReciveMemberId()
                )
        );
        model.addAttribute("foodCode", commentVO.getFoodCode());

        return "content/myPage/replace/commentOnly";
    }

    private List<CommentVO> sortReComment(List<CommentVO> commentList, List<CommentVO> reCommentList){
        return null;
    }

    @GetMapping("/weatherFood")
    public String allRegionList(RegionVO regionVO, Model model, Principal principal) {
        if(principal != null){
            model.addAttribute("recentViewList",FoodController.setCommentCnt(foodService.selectRecentView(principal.getName()),myPageService));
        }
        model.addAttribute("regionParents", weatherService.regionParent());
        model.addAttribute("regionChilds", weatherService.regionChild());
        return "/content/food/weatherFood";
    }

    @PostMapping("/ddabong")
    public String ddabongFoodList(@RequestBody WeatherVO weatherVO, RedirectAttributes redirectAttributes) {
        int temperature = Integer.parseInt(weatherVO.getTemperature());
        String precipitationType = weatherVO.getPrecipitationType();
        int baseTimeInt = Integer.parseInt(weatherVO.getBaseTime());
        String urlText = "";
        String ddabongCode = "";

        if (temperature <= 10) {
            if (temperature == -999) {
                ddabongCode = "DDABONG_006";
                urlText = "/img/weather/weatherBanner_normal.png";
            } else {
                urlText = "/img/weather/weatherBanner_cold.png";
                ddabongCode = "DDABONG_001";
            }
        } else if (temperature >= 25) {
            ddabongCode = "DDABONG_002";
            urlText = "/img/weather/weatherBanner_hot.png";
        } else if ("비".equals(precipitationType)) {
            ddabongCode = "DDABONG_003";
            urlText = "/img/weather/weatherBanner_rain.png";
        } else if ("눈".equals(precipitationType)) {
            ddabongCode = "DDABONG_004";
            urlText = "/img/weather/weatherBanner_snow.png";
        } else if (baseTimeInt >= 2300 || baseTimeInt < 800) {
            ddabongCode = "DDABONG_005";
            urlText = "/img/weather/weatherBanner_night.png";
        } else {
            ddabongCode = "DDABONG_006";
            urlText = "/img/weather/weatherBanner_normal.png";
        }

        List<DdabongVO> foodList = weatherService.ddabongFoodList(ddabongCode);
        System.err.println(foodList);
//        foodList = FoodController.setCommentCnt(foodList,myPageService);
        List<String> foodNames = new ArrayList<>();
        for (DdabongVO ddabong : foodList) {
            if (ddabong.getDdabongFood() != null) {
                String[] foodArray = ddabong.getDdabongFood().split(",\\s*");
                foodNames.addAll(Arrays.asList(foodArray));
            }
        }

        // foodNames가 비어 있는 경우 빈 리스트를 전달
        if (foodNames.isEmpty()) {
            redirectAttributes.addFlashAttribute("foodNames", new ArrayList<>());
        } else {
            redirectAttributes.addFlashAttribute("foodNames", foodNames);
        }
        redirectAttributes.addFlashAttribute("urlText", urlText);

        return "redirect:/food/ddabongRecipeList";
    }

    @GetMapping("/ddabongcode")
    public String ddabongCodeFoodList(DdabongVO ddabongVO, RedirectAttributes redirectAttributes, Model model, FoodVO foodVO, Principal principal) {
        String urlText = "";
//        if(ddabongVO.getDdabongCode() == null || ddabongVO.getDdabongCode().isEmpty()){
//            ddabongVO.setDdabongCode("DDABONG_006");
//        }
        List<DdabongVO> foodList = new ArrayList<>();
        if(ddabongVO.getDdabongCode() == null || ddabongVO.getDdabongCode().equals("DDABONG_006") || ddabongVO.getDdabongCode().isEmpty()){
            foodList = weatherService.ddabongFoodList("DDABONG_006");
            urlText = "/img/weather/weatherBanner_normal.png";
        } else{
            switch (ddabongVO.getDdabongCode()) {
                case "DDABONG_001":
                    urlText = "/img/weather/weatherBanner_cold.png";
                    break;
                case "DDABONG_002":
                    urlText = "/img/weather/weatherBanner_hot.png";
                    break;
                case "DDABONG_003":
                    urlText = "/img/weather/weatherBanner_rain.png";
                    break;
                case "DDABONG_004":
                    urlText = "/img/weather/weatherBanner_snow.png";
                    break;
                case "DDABONG_005":
                    urlText = "/img/weather/weatherBanner_night.png";
                    break;
                case "DDABONG_006":
                    urlText = "/img/weather/weatherBanner_normal.png";
                    break;
                default:
                    urlText = "/img/weather/weatherBanner_normal.png";
                    break;
            }
            foodList = weatherService.ddabongFoodList(ddabongVO.getDdabongCode());
        }




        List<String> foodNames = new ArrayList<>();
        for (DdabongVO ddabong : foodList) {
            if (ddabong.getDdabongFood() != null) {
                String[] foodArray = ddabong.getDdabongFood().split(",\\s*");
                foodNames.addAll(Arrays.asList(foodArray));
            }
        }
        String foodNamesString = foodNames.isEmpty() ? "" : String.join(",", foodNames);

        List<FoodVO> ddabongFoodList;
        List<String> foodNames2 = new ArrayList<>();

        if (foodNamesString != null && !foodNamesString.isEmpty()) {
            foodNames2 = Arrays.asList(foodNamesString.split(","));
        }
        foodVO.setFoodNames(foodNames2);
        if (foodNames2.isEmpty()) {
            // foodNames가 비어있는 경우 모든 데이터를 조회하도록 처리
            foodVO.setTotalDataCnt(foodService.allRecipeCount());
            foodVO.setPageInfo();
            ddabongFoodList = foodService.allRecipeListPage(foodVO);
        } else {
            foodVO.setTotalDataCnt(foodService.ddabongRecipeListPageCnt(foodVO));
            foodVO.setPageInfo();
            ddabongFoodList = foodService.ddabongRecipeListPage(foodVO);
        }
        System.err.println(foodVO.getNowPage());
        System.err.println(foodVO);
        model.addAttribute("ddabongCode", ddabongVO.getDdabongCode());
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        model.addAttribute("nowPage", foodVO.getNowPage());
        model.addAttribute("urlText", urlText);
        ddabongFoodList = FoodController.setCommentCnt(ddabongFoodList,myPageService);
        model.addAttribute("foodList", ddabongFoodList);

        if(principal != null){
            model.addAttribute("recentViewList",FoodController.setCommentCnt(foodService.selectRecentView(principal.getName()),myPageService));
        }


        return "content/food/weatherFood_direct";
    }

//    @GetMapping("/ddabongCodeRecipeList")
//    public String ddabongCodeRecipeList(@ModelAttribute("foodNames") String foodNamesString, @ModelAttribute("urlText") String urlText, Model model, FoodVO foodVO) {
//        System.err.println(foodNamesString);
//        FoodVO foodVO1 = new FoodVO();
//        List<FoodVO> ddabongFoodList;
//        List<String> foodNames = new ArrayList<>();
//
//        if (foodNamesString != null && !foodNamesString.isEmpty()) {
//            foodNames = Arrays.asList(foodNamesString.split(","));
//        }
//
//        if (foodNames.isEmpty()) {
//            // foodNames가 비어있는 경우 모든 데이터를 조회하도록 처리
//            ddabongFoodList = foodService.allRecipeList();
//        } else {
//            ddabongFoodList = foodService.ddabongRecipeList(foodNames);
//        }
//        model.addAttribute("foodUsageList", foodService.foodUsageList());
//        model.addAttribute("foodKindList", foodService.foodKindList());
//        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
//        model.addAttribute("foodTypeList", foodService.foodTypeList());
//        foodVO1.setPageInfo();
//        model.addAttribute("nowPage", foodVO1.getNowPage());
//        model.addAttribute("urlText", urlText);
//        ddabongFoodList = FoodController.setCommentCnt(ddabongFoodList,myPageService);
//        model.addAttribute("foodList", ddabongFoodList);
//        return "content/food/weatherFood_direct";
//    }

    @GetMapping("/ddabongRecipeList")
    public String ddabongRecipeList(Principal principal,@ModelAttribute("foodNames") List<String> foodNames, @ModelAttribute("urlText") String urlText, Model model) {
        if (foodNames == null) {
            foodNames = new ArrayList<>();
        }

        List<FoodVO> ddabongFoodList;
        if (foodNames.isEmpty()) {
            ddabongFoodList = foodService.allRecipeList();
        } else {
            ddabongFoodList = foodService.ddabongRecipeList(foodNames);
        }
        ddabongFoodList = FoodController.setCommentCnt(ddabongFoodList, myPageService);
        if(principal != null){
            model.addAttribute("recentViewList", FoodController.setCommentCnt(foodService.selectRecentView(principal.getName()),myPageService));
        }
        model.addAttribute("urlText", urlText);
        model.addAttribute("foodList", ddabongFoodList);
        return "content/food/ddabongRecipeList";
    }


    // 레시피 등록 페이지로 이동
    @GetMapping("/insertFoodForm")
    public String insertFoodForm(Model model, Principal principal, MemberVO memberVO) {
        MemberVO memberVO1 = new MemberVO();
        memberVO1.setMemberName((memberService.selectMemberName(principal.getName())).getMemberName());
        memberVO1.setMemberId(principal.getName());

        model.addAttribute("memberInfo", memberVO1);
        model.addAttribute("foodUsageList", foodService.foodUsageList());
        model.addAttribute("foodKindList", foodService.foodKindList());
        model.addAttribute("foodMtrlList", foodService.foodMtrlList());
        model.addAttribute("foodTypeList", foodService.foodTypeList());
        return "/content/food/food_insert";
    }

    @PostMapping("/recipeInsert")
    public String recipeInsert(RedirectAttributes redirectAttributes,FoodVO foodVO, FoodStepsVO foodStepsVO,FoodImgVO foodImgVO, @RequestParam("foodImg") MultipartFile foodImg) {
        String foodCode = foodService.nextFoodCode();
        FoodImgVO uploadedImg = UploadUtillFoodImg.uploadFile(foodImg);
        uploadedImg.setFoodCode(foodCode);
        foodVO.setFoodCode(foodCode);
        foodStepsVO.setFoodCode(foodCode);
        foodService.insertFood(foodVO, foodStepsVO, uploadedImg);
        redirectAttributes.addAttribute("foodCode", foodCode);
        return "redirect:/food/detail";
    }

    @GetMapping("/updateFoodForm")
    public String updateFoodForm(FoodVO foodVO, Model model){
        foodService.updateFoodInqCnt(foodVO);
        model.addAttribute("foodDetail", foodService.getFoodDtl(foodVO));
        FoodVO detailFoodVO = foodService.getFoodDtl(foodVO);
        model.addAttribute("foodCodeList", foodService.selectFoodCode(detailFoodVO));
        setupSearchDetails(model, foodVO);
        String mtrl = detailFoodVO.getFoodMtrlCn();
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]([^\\[]+)(?=\\[|$)");
        Matcher matcher = pattern.matcher(mtrl);

        List<String> mtrlTitle = new ArrayList<>();
        List<List<String>> mtrlMt1 = new ArrayList<>();
        List<List<String>> mtrlMt2 = new ArrayList<>();

        while (matcher.find()) {
            mtrlTitle.add(matcher.group(1).trim());
            String materials = matcher.group(2).trim();
            String[] mtrls = materials.split("\\|");
            for (int i = 0; i < mtrls.length; i++) {
                String[] splitMtrls = mtrls[i].trim().split("\\s+(?=[^\\s]*$)");
                List<String> mtrlList = Arrays.asList(splitMtrls);
                if (mtrlTitle.size() == 1) {
                    mtrlMt1.add(mtrlList);
                } else {
                    mtrlMt2.add(mtrlList);
                }
            }
        }

        // 결과 출력
        model.addAttribute("foodImg", foodService.selectFoodImg(foodVO));
        model.addAttribute("mtrlTitles", mtrlTitle);
        model.addAttribute("mtrlMt1", mtrlMt1);
        if (mtrlMt2.isEmpty()) {
            mtrlMt2.add(new ArrayList<>());
        }
        model.addAttribute("mtrlMt2", mtrlMt2);
        if(foodService.getFoodSteps(foodVO) != null && foodService.getFoodSteps(foodVO).size() > 0){
            String foodStepsStr = String.valueOf(foodService.getFoodSteps(foodVO).get(0).getFoodEx());
            List<String> foodStepsList = Arrays.asList(foodStepsStr.split("%"));
            model.addAttribute("foodSteps", foodStepsList);
        }else{
            model.addAttribute("foodSteps",foodService.getFoodSteps(foodVO));
        }
        return "/content/food/food_update";
    }

    @PostMapping("/foodUpdate")
    public String foodUpdate(RedirectAttributes redirectAttributes,FoodVO foodVO, FoodStepsVO foodStepsVO,FoodImgVO foodImgVO, @RequestParam("foodImg") MultipartFile foodImg) {
        String foodCode = foodVO.getFoodCode();
        FoodImgVO uploadedImg = UploadUtillFoodImg.uploadFile(foodImg);

        if (uploadedImg != null) {
            uploadedImg.setFoodCode(foodCode);
            if (foodService.selectFoodImg(foodVO) != null) {
                foodService.updateFood(foodVO, foodStepsVO, uploadedImg);
            } else if (foodService.selectFoodImg(foodVO) == null) {
                foodService.updateAndInsertImg(foodVO, foodStepsVO, uploadedImg);
            }
        }else{
            foodService.updateFood(foodVO, foodStepsVO);
        }

        redirectAttributes.addAttribute("foodCode", foodCode);
        return "redirect:/food/detail";
    }

    @ResponseBody
    @PostMapping("/deleteFood")
    public String deleteFood (FoodVO foodVO){
        foodService.deleteFood(foodVO);
        return "성공";
    }

}