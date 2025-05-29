package com.drc.jobapplicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobPortalDto {

    private Long id;

    private String name;
    private String url;
    private String careerUrl;
}
