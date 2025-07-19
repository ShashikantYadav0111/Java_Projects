package com.project.url_shortner.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
    private boolean isActive;
    private String longUrl;
}
