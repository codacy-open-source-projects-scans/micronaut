package io.micronaut.logback.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Controller("/teapot")
public class TeapotController {

    public static final HttpResponse<String> RESPONSE_WITH_CUSTOM_STATUS = HttpResponse.status(999, "My custom reason");

    @Get("/custom-status")
    public HttpResponse<String> customStatus() {
        return RESPONSE_WITH_CUSTOM_STATUS;
    }

    @Get("/sync-teapot")
    public HttpResponse<String> syncNotFound() {
        return teapotResponse();
    }

    @Get("/async-teapot")
    public CompletableFuture<HttpResponse<String>> asyncNotFound() {
        return CompletableFuture.supplyAsync(this::teapotResponse);
    }

    @Get("/reactive-teapot")
    public Mono<HttpResponse<String>> reactiveNotFound() {
        return Mono.just(teapotResponse());
    }

    private HttpResponse<String> teapotResponse() {
        return HttpResponse.status(HttpStatus.I_AM_A_TEAPOT);
    }
}
