package com.example.direction.service

import spock.lang.Specification

class Base62ServiceTest extends Specification {

    private Base62Service base62Service;

    def setup() {
        base62Service = new Base62Service()
    }

    def "check base62 encoder/decoder"() {
        given:
        long id = 5L

        when:
        def encodedId = base62Service.encodeDirectionId(id)
        def decodedId = base62Service.decodeDirectionId(encodedId)

        then:
        id == decodedId
    }

}
