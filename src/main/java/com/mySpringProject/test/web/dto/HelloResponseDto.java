package com.mySpringProject.test.web.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드를 생성합니다.
@RequiredArgsConstructor // 선언된 모든 필드가 포함된 생성자를 생성해줍니다. 단 final이 있는 필드만 포함됩니다.
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
