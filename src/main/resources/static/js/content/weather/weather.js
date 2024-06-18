$(document).ready(function() {
    var arr = [];
    var today = new Date();
    var week = new Array('일', '월', '화', '수', '목', '금', '토');
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var hours = today.getHours();
    var minutes = today.getMinutes();
    var hours_al = new Array('02', '05', '08', '11', '14', '17', '20', '23');
    var korea = [
        {'region': '서울', 'nx': 60, 'ny': 127},
        {'region': '인천', 'nx': 55, 'ny': 124},
        {'region': '경기도', 'nx': 60, 'ny': 121},
        {'region': '강원도', 'nx': 92, 'ny': 131},
        {'region': '충청북도', 'nx': 69, 'ny': 106},
        {'region': '충청남도', 'nx': 68, 'ny': 100},
        {'region': '전라북도', 'nx': 63, 'ny': 89},
        {'region': '전라남도', 'nx': 50, 'ny': 67},
        {'region': '경상남도', 'nx': 90, 'ny': 77},
        {'region': '경상북도', 'nx': 91, 'ny': 106},
        {'region': '제주도', 'nx': 52, 'ny': 38}
    ];

    for (var i = 0; i < hours_al.length; i++) {
        var h = hours_al[i] - hours;
        if (h == -1 || h == 0 || h == -2) {
            var now = hours_al[i];
        }
        if (hours == 0) {
            var now = hours_al[7];
        }
    }

    if (hours < 10) {
        hours = '0' + hours;
    }
    if (month < 10) {
        month = '0' + month;
    }
    if (day < 10) {
        day = '0' + day;
    }

    today = year + "" + month + "" + day;

    $.each(korea, function(j, k) {
        var _nx = korea[j].nx,
            _ny = korea[j].ny,
            region = korea[j].region,
            apikey = "3Rb5ATbVd0%2BgY821Hz8%2F%2F7ZsRJUK0o%2FtJsjpaoMRh%2F8MSD3%2B1HPjEzTtMyYOhq37TtKEdFpTMZEOJDsDAnyx2A%3D%3D",
            ForecastGribURL = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
        ForecastGribURL += "?ServiceKey=" + apikey;
        ForecastGribURL += "&base_date=" + today;
        ForecastGribURL += "&base_time=" + now + "00";
        ForecastGribURL += "&nx=" + _nx + "&ny=" + _ny;
        ForecastGribURL += "&dataType=JSON"; // JSON 형식으로 요청

        arr.push({'url': ForecastGribURL, 'region': region});

        $.ajax({
            url: arr[j].url,
            type: 'GET',
            dataType: 'json', // JSON 형식으로 응답 받기
            success: function(data) {
                var items = data.response.body.items.item;
                var temp = '';
                var sky = '';
                var rain = '';

                $.each(items, function(i, item) {
                    if (item.category == 'T3H') {
                        temp = item.fcstValue; // 3시간 온도
                    }
                    if (item.category == 'SKY') {
                        sky = item.fcstValue; // 하늘상태
                    }
                    if (item.category == 'PTY') {
                        rain = item.fcstValue; // 강수형태
                    }
                });

                $('.weather').append('<li class="list-group-item weather_li' + j + '"></li>');
                $('.weather_li' + j).addClass('in' + j);
                $('.in' + j).html(temp + " ℃"); // 온도
                $('.in' + j).prepend(arr[j].region + '&emsp;'); // 지역이름

                if (rain != 0) {
                    switch (rain) {
                        case '1':
                            $('.in' + j).append(" / 비");
                            $('.in' + j).prepend('<i class="fas fa-cloud-showers-heavy"></i>&emsp;');
                            break;
                        case '2':
                            $('.in' + j).append(" / 비/눈");
                            $('.in' + j).prepend('<i class="fas fa-cloud-rain"></i>&emsp;');
                            break;
                        case '3':
                            $('.in' + j).append(" / 눈");
                            $('.in' + j).prepend('<i class="fas fa-snowflake"></i>&emsp;');
                            break;
                    }
                } else {
                    switch (sky) {
                        case '1':
                            $('.in' + j).append(" / 맑음");
                            $('.in' + j).prepend('<i class="fas fa-sun"></i>&emsp;');
                            break;
                        case '2':
                            $('.in' + j).append(" / 구름조금");
                            $('.in' + j).prepend('<i class="fas fa-cloud-sun"></i>&emsp;');
                            break;
                        case '3':
                            $('.in' + j).append(" / 구름많음");
                            $('.in' + j).prepend('<i class="fas fa-cloud"></i>&emsp;');
                            break;
                        case '4':
                            $('.in' + j).append(" / 흐림");
                            $('.in' + j).prepend('<i class="fas fa-smog"></i>&emsp;');
                            break;
                    }
                }
            }
        });
    });
});
