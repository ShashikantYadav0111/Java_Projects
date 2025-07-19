package com.project.url_shortner.controller;

import com.project.url_shortner.dto.ResponseDto;
import com.project.url_shortner.dto.UrlRequestDto;
import com.project.url_shortner.model.UrlData;
import com.project.url_shortner.service.UrlDataService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("short")
@Slf4j
public class UrlDataController {

    private final UrlDataService urlDataService;

    public UrlDataController(UrlDataService urlDataService){
        this.urlDataService = urlDataService;
    }

    @PostMapping("shorten")
    @ResponseStatus(HttpStatus.CREATED)
    public String shortenUrl(@RequestBody UrlRequestDto requestDto){
        String shortCode = urlDataService.shortenUrlMethod(requestDto.getLongUrl());
        return "http://localhost:8085/short/"+shortCode;
    }


    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode,HttpServletResponse httpResponse) throws IOException {
        final ResponseDto response = urlDataService.getLongUrl(shortCode);
        try{
            if(response.isActive()){
                System.out.println(response.getLongUrl());
                String longUrl = response.getLongUrl();
                httpResponse.sendRedirect(longUrl);
        }else{
            httpResponse.sendError(HttpStatus.GONE.value(),"Link Expired or Doesn't Exist!!!");
        }
        }catch(IllegalArgumentException e){
            httpResponse.sendError(HttpStatus.BAD_REQUEST.value(),"Something went wrong");
            log.warn(e.getMessage());
        }
    }
}
