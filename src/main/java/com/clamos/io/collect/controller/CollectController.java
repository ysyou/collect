package com.clamos.io.collect.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/collect")
@RequiredArgsConstructor
public class CollectController {
    //수집 시작하는 컨트롤러
    //1. 파이프라인이 생성된다
    //2. 토픽을 동적으로 만든다
    //3. 수집해야할 정보를 받는다. (여기에 scheduler 인지 CDC인지에 대한 정보도있어야된다)
    //4, 수집 시작한다.
    //5. 다끝나면 CDC 설정 들어간다. (CDC설정인경우)
    //5. 수집된 데이터는 바로 topic에 던진다




    /*   회의 필요 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!     */
    //초기데이터 수집 후 -> CDC 처리 -> 중간 트랜잭션 데이터를 놓칠 수 있음 *** 단점
    //CDC 처리 후 -> 초기데이터 수집 -> 데이터가 upsert 의 개념이 있는 카프카가 아니여서 중복데이터 문제 발생할 수 잇음 -> kafka 에서 업설트 할 수 있는 부분이있는지 확인필요 -> 일단 이방식으로
    //문제는 데이터가 중복인지 같은데이터가 insert 되는건지 어떻게 구분함? 이거 문제잇음



}
