package com.example.api.service

import com.example.AbstractIntegrationContainerBaseTest

import java.nio.charset.StandardCharsets

class KakaoUriBuilderServiceTest extends AbstractIntegrationContainerBaseTest {

    private KakaoUriBuilderService kakaoUriBuilderService

    void setup() {
        kakaoUriBuilderService = new KakaoUriBuilderService()
    }

    void "buildUriByAddressSearch - 한글 파라미터의 경우 정상적으로 인코딩"() {
        given:
        String address = "서울 성북구"
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUriBuilderService.buildUriByAddressSearch(address)
        def decodedResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodedResult == "https://dapi.kakao.com/v2/local/search/address.json?query=서울 성북구"
    }
}
