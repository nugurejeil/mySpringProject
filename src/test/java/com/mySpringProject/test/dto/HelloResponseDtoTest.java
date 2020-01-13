package com.mySpringProject.test.dto;
import com.mySpringProject.test.web.dto.HelloResponseDto;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // assertThat : 검증하고싶은 대상을 인자로 받습니다.
        assertThat(dto.getAmount()).isEqualTo(amount); // isEqualTo :  assertThat 의 값과 같은지 비교합니다.

    }
}
