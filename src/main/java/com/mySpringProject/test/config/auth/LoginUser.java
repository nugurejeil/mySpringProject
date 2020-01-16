package com.mySpringProject.test.config.auth;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치를 지정합니다. PARAMETER로 지정했으니 메소드의 파라메터로 선언된 객체에서만 사용할 수 있습니다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME) // 컴파일 이후에도 JVM에 의해 계속 참조가 가능하게 했습니다.
public @interface LoginUser { // 이 파일을 어노테이션 클래스로 지정합니다. LoginUser 라는 이름의 어노테이션이 생성됐다고 보면됩니다.
}
