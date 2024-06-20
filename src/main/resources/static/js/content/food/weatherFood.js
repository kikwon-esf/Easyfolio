
document.addEventListener("DOMContentLoaded", function () {
    const firstParentBox = document.querySelector('.parentBox');
    if (firstParentBox) {
        firstParentBox.classList.add('selected'); 
        const firstChildBlock = firstParentBox.nextElementSibling;
        if (firstChildBlock) {
            firstChildBlock.classList.add('on');
            const firstChildBox = firstChildBlock.querySelector('.regionChildBox');
            if (firstChildBox) {
                firstChildBox.classList.add('selected'); 
                updateWeatherArea(firstChildBox);
                callWeatherAPI(firstChildBox);
            }
        }
    }
});
function updateWeatherArea(childElement) {
    const childText = childElement.querySelector('.regionChildText').textContent.trim();
    document.querySelector('.childArea').textContent = childText;

    const parentText = childElement.closest('.regionParentBlock').querySelector('.parentBox').textContent.trim();
    document.querySelector('.parentArea').textContent = parentText;
}

function toggleRegionChildBox(parentElement) {
    const currentOnBlock = document.querySelector('.regionChildBlock.on');
    if (currentOnBlock) {
        currentOnBlock.classList.remove('on');
        const currentSelectedBox = currentOnBlock.querySelector('.regionChildBox.selected');
        if (currentSelectedBox) {
            currentSelectedBox.classList.remove('selected');
        }
    }

    const selectedChildBlock = parentElement.nextElementSibling;
    if (selectedChildBlock) {
        selectedChildBlock.classList.add('on');
        const firstChildBox = selectedChildBlock.querySelector('.regionChildBox');
        if (firstChildBox) {
            firstChildBox.classList.add('selected');
            selectRegionChildBox(firstChildBox);
        }
    }

    const allParentBoxes = document.querySelectorAll('.parentBox');
    allParentBoxes.forEach(box => box.classList.remove('selected'));

    parentElement.classList.add('selected');
}
function selectRegionChildBox(childElement) {
    const allChildBoxes = document.querySelectorAll('.regionChildBox');
    allChildBoxes.forEach(box => box.classList.remove('selected'));

    childElement.classList.add('selected');
    callWeatherAPI(childElement);

    updateWeatherArea(childElement);
}

function callWeatherAPI(regionChildBox) {
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더해준다.
    var day = ('0' + currentDate.getDate()).slice(-2);
    var hours = ('0' + currentDate.getHours()).slice(-2);
    var minutes = ('0' + currentDate.getMinutes()).slice(-2);

    // 현재 시간 기준으로 baseTime 설정
    var baseTime = "";
    if (hours >= "23" || hours < "02") {
        baseTime = "2300";
        // 자정 이후부터 새벽 2시 이전까지는 전날 2300을 기준으로 함
        var prevDate = new Date(currentDate);
        prevDate.setDate(currentDate.getDate() - 1);
        year = prevDate.getFullYear();
        month = ('0' + (prevDate.getMonth() + 1)).slice(-2);
        day = ('0' + prevDate.getDate()).slice(-2);
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

    var baseDate = `${year}${month}${day}`;
    var baseTime1 = `${hours}00`;
    var apiKey = "3Rb5ATbVd0%2BgY821Hz8%2F%2F7ZsRJUK0o%2FtJsjpaoMRh%2F8MSD3%2B1HPjEzTtMyYOhq37TtKEdFpTMZEOJDsDAnyx2A%3D%3D";
    var nx = regionChildBox.querySelector('.nxValue').value;
    var ny = regionChildBox.querySelector('.nyValue').value;
    var apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    var dataType = "JSON";

    var url = `${apiUrl}?ServiceKey=${apiKey}&base_date=${baseDate}&base_time=${baseTime}&nx=${nx}&ny=${ny}&dataType=${dataType}`;

    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            var temperature = null;
            var skyCondition = null;
            var precipitationType = null;
            var precipitationProbability = null;

            console.log(data);

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
                        break;
                }
            }

            document.querySelector('.weatherTemp').textContent = temperature;
            document.querySelector('.skyState').textContent = skyCondition;
            document.querySelector('.rainPer').textContent = precipitationProbability;
            document.querySelector('.rainState').textContent = precipitationType;

            updateWeatherImage(skyCondition, precipitationType);

            updateFoodRecommendations(items[0].fcstValue, precipitationType);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("API 요청 실패:", textStatus, errorThrown);
        }
    });
}

function updateWeatherImage(skyCondition, precipitationType) {
    let imgSrc = "";
    const currentHour = new Date().getHours();
    if (precipitationType !== "없음" && precipitationType !== "알 수 없음") { 
        switch (precipitationType) {
            case "비":
                imgSrc = "/img/weather/weather_rain.svg";
                break;
            case "비/눈":
                imgSrc = "/img/weather/weather_rainNsnow.svg";
                break;
            case "눈":
                imgSrc = "/img/weather/weather_snow.svg";
                break;
            case "소나기":
                imgSrc = "/img/weather/weather_sunNrain.svg";
                break;
            default:
                imgSrc = "/img/weather/weather_sun.svg";  
                break;
        }
    } else {
        switch (skyCondition) {
            case "맑음":
                if (currentHour >= 18) {
                    imgSrc = "/img/weather/weather_moon.svg";
                } else {
                    imgSrc = "/img/weather/weather_sun.svg";
                }
                break;
            case "구름많음":
                if (currentHour >= 18) { 
                    imgSrc = "/img/weather/weather_nightCloud.svg";
                } else {
                    imgSrc = "/img/weather/weather_dayCloud.svg";
                }
                break;
            case "흐림":
                imgSrc = "/img/weather/weather_cloud.svg";
                break;
            default:
                imgSrc = "/img/weather/weather_sun.svg";  
                break;
        }
    }


    document.querySelector('.weatherImg img').src = imgSrc; 
}

function updateFoodRecommendations(fcstValue, precipitationType) {
    console.log(fcstValue, precipitationType);
    fetch('/food/ddabong', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'temperature': fcstValue,
            'precipitationType': precipitationType
        })
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
        }
        return response.text(); // 응답을 텍스트로 받기
    })
    .then((html) => {
        console.log(html);
        document.querySelector('.ddabongRecipeContainer').innerHTML = html; // 지정된 컨테이너에 HTML 교체
    })
    .catch(err => {
        alert('fetch error!\n' + err.message + '\n콘솔창을 확인하세요!');
        console.error(err);
    });
}


document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;

    var sideFormElements = document.querySelectorAll('.foodTap');
    sideFormElements.forEach(function (element) {
        var link = element.closest('a') ? element.closest('a').getAttribute('href') : null;
        
        if (link && currentPage === link) {
            element.classList.add('selected'); 
        }
    });
});