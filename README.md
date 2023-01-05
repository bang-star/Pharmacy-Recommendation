# Pharmacy-Recommendation

[외부 API(카카오 주소 검색 API)](https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-category)와 [공공 데이터(약국 현황 정보)](https://www.data.go.kr/data/15065023/fileData.do)를 활용함으로써 혼자 개발하고 마무리 되는 프로젝트가 아닌 실제 서비스 가능한 수준의 프로젝트를 경험해본다.

추천된 약국의 길 안내는 [카카오 지도 및 로드뷰 바로가기 URL](https://apis.map.kakao.com/web/guide/#routeurl)로 제공된다.

약국 도메인이 아닌 자신만의 추천 서비스 구현이 가능해질 것이며, [카카오 키워드 장소 검색 API](https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-category)를 이용하여 약국 외에 편의점, 지하철역, 음식점 등 다른 도메인으로 적용 가능함을 확인할 수 있다. 
즉 다른 도메인의 데이터 또는 API가 존재한다면 자신만의 추천 서비스를 구현해 볼 수 있다.

## 요구사항 분석

 * 약국 현황 데이터(공공 데이터)를 관리하고 있다라고 가정하고, 약국 현황 데이터는 위도 경도의 위치 정보 데이터를 가지고 있다.
 
 * 해당 서비스로 주소 정보를 입력하면 위치 기준에서 가까운 약국 3곳을 추천한다.
 * 주소는 도로명 주소 또는 지번을 입력하여 요청 받는다.
   * 정확한 주소를 입력 받기 위해 [Kakao 우편번호 서비스](https://postcode.map.daum.net/guide)를 이용한다.
 * 주소는 정확한 상세 주소(동, 호수)를 제외한 주소 정보를 이용하여 추천한다. 
   * EX) 서울 성북구 종암로 10길
 * 입력받은 주소를 위도, 경도로 변환하여 기존 약국 데이터와 비교 및 가까운 약국을 찾는다. 
   * 지구는 평면이 아니기 때문에, 구면에서 두 점 사이의 최단 거리 구하는 공식이 필요하다. 
   * 두 위 경도 좌표 사이의 거리를 [haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)로 계산한다. 
   * 지구가 완전한 구형이 아니므로 오차가 발생할 수 있다. 
    
 * 입력한 주소 정보에서 정해진 반경(10km) 내에 있는 약국만 추천한다. 
 * 추출한 약국 데이터는 길안내 URL 및 로드뷰 URL로 제공한다. 
   * 길안내 URL : https://map.kakao.com/link/map/우리회사,37.402056,127.108212
   * 로드뷰 URL : https://map.kakao.com/link/roadview/37.402056,127.108212
 * 길안내 URL은 고객에게 제공되기 때문에 가독성을 위해 shorten url로 제공한다. 
 * shorten url에 사용되는 Key 값은 인코딩하여 제공한다. 
   * ex) http://localhost:8080/dir/nqxtxd
   * bas62 를 통한 인코딩 
 * shorten url의 유효 기간을 30일로 제한한다.

## Pharmacy Recommendation Process

<img src="https://user-images.githubusercontent.com/63120360/210836803-6ca550f1-3207-4b4e-9901-b7907364bec0.svg">

## Direction Shorten Url Process

<img src="https://user-images.githubusercontent.com/63120360/210839565-336d3e21-a19c-4bfb-8d49-f3ad1f6e3a33.svg">

## Tech Stack

 - JDK 17
 - Spring Boot 2.7.7 
 - Spring Data JPA 
 - Gradle 
 - Handlebars 
 - Lombok 
 - Github 
 - Docker 
 - AWS EC2
 - Redis 
 - MariaDB 
 - Spock 
 - Testcontainers
