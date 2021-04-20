package dev.be.goodgid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.be.goodgid.domain.Member;
import dev.be.goodgid.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @Operation(summary = "Test API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "400", description = "Client Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Member.class))),
    })
    @PostMapping("/test")
    public String test(@RequestBody Member member) {
        if (member.getId() == 1L){
            throw new RuntimeException("run time exception");
        }
        return testService.checkDB();
    }
}
