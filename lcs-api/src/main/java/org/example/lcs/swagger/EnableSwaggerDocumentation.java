package org.example.lcs.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import(SwaggerDocumentation.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableSwaggerDocumentation {
}
