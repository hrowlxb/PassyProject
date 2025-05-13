package com.hrowlxb.passy_backend.savedsite.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SavedSiteRequestDto {

    private String siteName;
    private String siteUrl;
    private String loginId;
    private String password;
}
