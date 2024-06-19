
document.addEventListener("DOMContentLoaded", function() {
    // 첫 번째 parentBox와 그에 해당하는 regionChildBlock, 첫 번째 regionChildBox를 표시
    const firstParentBox = document.querySelector('.parentBox');
    if (firstParentBox) {
        firstParentBox.classList.add('selected'); // 선택된 parentBox에 클래스 추가
        const firstChildBlock = firstParentBox.nextElementSibling;
        if (firstChildBlock) {
            firstChildBlock.classList.add('on');
            const firstChildBox = firstChildBlock.querySelector('.regionChildBox');
            if (firstChildBox) {
                firstChildBox.classList.add('selected'); // 첫 번째 regionChildBox에 클래스 추가
                callWeatherAPI(firstChildBox);
            }
        }
    }
});

function toggleRegionChildBox(parentElement) {
    // 기존의 on 클래스를 가진 regionChildBlock에서 on 클래스 제거
    const currentOnBlock = document.querySelector('.regionChildBlock.on');
    if (currentOnBlock) {
        currentOnBlock.classList.remove('on');
        // 현재 선택된 regionChildBox에서 selected 클래스 제거
        const currentSelectedBox = currentOnBlock.querySelector('.regionChildBox.selected');
        if (currentSelectedBox) {
            currentSelectedBox.classList.remove('selected');
        }
    }

    // 선택된 parentBox와 그에 해당하는 regionChildBlock에 on 클래스 추가
    const selectedChildBlock = parentElement.nextElementSibling;
    if (selectedChildBlock) {
        selectedChildBlock.classList.add('on');
        // 첫 번째 regionChildBox에 selected 클래스 추가
        const firstChildBox = selectedChildBlock.querySelector('.regionChildBox');
        if (firstChildBox) {
            firstChildBox.classList.add('selected');
            selectRegionChildBox(firstChildBox);
        }
    }
    
    // 모든 parentBox의 selected 클래스 제거
    const allParentBoxes = document.querySelectorAll('.parentBox');
    allParentBoxes.forEach(box => box.classList.remove('selected'));
    
    // 클릭된 parentBox에 selected 클래스 추가
    parentElement.classList.add('selected');
}
function selectRegionChildBox(childElement) {
    // 모든 regionChildBox에서 selected 클래스 제거
    const allChildBoxes = document.querySelectorAll('.regionChildBox');
    allChildBoxes.forEach(box => box.classList.remove('selected'));

    // 클릭된 regionChildBox에 selected 클래스 추가
    childElement.classList.add('selected');
    callWeatherAPI(childElement);
}



// TMP = 기온
// SKY = 하늘 상태	코드값	맑음(1), 구름많음(3), 흐림(4)
// PTY = 강수형태	코드값	없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
// POP = 강수확률 	%
// PCP 1시간 강수량	범주 1mm
function callWeatherAPI(regionChildBox){
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더해준다.
    var day = ('0' + currentDate.getDate()).slice(-2);
    var hours = ('0' + currentDate.getHours()).slice(-2);
    var minutes = ('0' + currentDate.getMinutes()).slice(-2);
    var baseTime = "";
    if (hours >= "23" || hours < "02") {
        baseTime = "2300";
    } else if (hours < "05") {
        baseTime = "0200";
    } else if (hours < "08") {
        baseTime = "0500";
    } else if (hours < "11") {
        baseTime = "0800";
    } else if (hours < "14") {
        baseTime = "1100";
    } else if (hours < "17") {
        baseTime = "1400";
    } else if (hours < "20") {
        baseTime = "1700";
    } else if (hours < "23") {
        baseTime = "2000";
    }
    var baseDate1 = `${year}${month}${day}`;
    var baseTime1 = `${hours}00`;
    console.log(baseDate1)
    console.log(baseTime1)
    var apiKey = "3Rb5ATbVd0%2BgY821Hz8%2F%2F7ZsRJUK0o%2FtJsjpaoMRh%2F8MSD3%2B1HPjEzTtMyYOhq37TtKEdFpTMZEOJDsDAnyx2A%3D%3D";
    var baseDate = `${year}${month}${day}`;
    var nx = regionChildBox.querySelector('.nxValue').value;
            var ny = regionChildBox.querySelector('.nyValue').value;
    var apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    var dataType = "JSON";

    // API 요청 URL 생성
    var url = `${apiUrl}?ServiceKey=${apiKey}&base_date=${baseDate}&base_time=${baseTime}&nx=${nx}&ny=${ny}&dataType=${dataType}`;

    // AJAX를 통한 비동기 요청
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var temperature = null;
            var skyCondition = null;
            var precipitationType = null;
            var precipitationProbability = null;

            var items = data.response.body.items.item;
            for (var i = 0; i < items.length; i++) {
                var category = items[i].category;
                var fcstValue = items[i].fcstValue;

                switch (category) {
                    case "TMP":
                        temperature = fcstValue + " ℃";
                        break;
                    case "SKY":
                        switch (fcstValue) {
                            case "1":
                                skyCondition = "맑음";

                                break;
                            case "3":
                                skyCondition = "구름많음";
                                break;
                            case "4":
                                skyCondition = "흐림";
                                break;
                            default:
                                skyCondition = "알 수 없음";
                                break;
                        }
                        break;
                    case "PTY":
                        switch (fcstValue) {
                            case "0":
                                precipitationType = "없음";
                                break;
                            case "1":
                                precipitationType = "비";
                                break;
                            case "2":
                                precipitationType = "비/눈";
                                break;
                            case "3":
                                precipitationType = "눈";
                                break;
                            case "4":
                                precipitationType = "소나기";
                                break;
                            default:
                                precipitationType = "알 수 없음";
                                break;
                        }
                        break;
                    case "POP":
                        precipitationProbability = fcstValue + " %";
                        break;
                    default:
                        // 다른 카테고리의 경우 아무 작업도 하지 않음
                        break;
                }
            }

            // 추출한 값을 출력해보기
            console.log("기온:", temperature);
            console.log("하늘 상태:", skyCondition);
            console.log("강수 형태:", precipitationType);
            console.log("강수 확률:", precipitationProbability);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 요청 실패 시 에러 처리
            console.error("API 요청 실패:", textStatus, errorThrown);
        }
    });
};
