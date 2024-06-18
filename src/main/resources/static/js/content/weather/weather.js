// TMP = 기온
// SKY = 하늘 상태	코드값	맑음(1), 구름많음(3), 흐림(4)
// PTY = 강수형태	코드값	없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
// POP = 강수확률 	%
// PCP 1시간 강수량	범주 1mm
$(document).ready(function() {
    var apiKey = "3Rb5ATbVd0%2BgY821Hz8%2F%2F7ZsRJUK0o%2FtJsjpaoMRh%2F8MSD3%2B1HPjEzTtMyYOhq37TtKEdFpTMZEOJDsDAnyx2A%3D%3D";
    var baseDate = "20240618";
    var baseTime = "2000";
    var nx = 55;
    var ny = 127;
    var apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    var dataType = "JSON";

    // API 요청 URL 생성
    var url = `${apiUrl}?ServiceKey=${apiKey}&base_date=${baseDate}&base_time=${baseTime}&nx=${nx}&ny=${ny}&dataType=${dataType}`;

    // AJAX를 통한 비동기 요청
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
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
        error: function(jqXHR, textStatus, errorThrown) {
            // 요청 실패 시 에러 처리
            console.error("API 요청 실패:", textStatus, errorThrown);
        }
    });
});
