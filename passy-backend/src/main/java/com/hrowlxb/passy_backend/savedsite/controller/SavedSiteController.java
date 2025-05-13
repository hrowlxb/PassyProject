package com.hrowlxb.passy_backend.savedsite.controller;

import com.hrowlxb.passy_backend.global.security.JwtTokenProvider;
import com.hrowlxb.passy_backend.savedsite.domain.SavedSite;
import com.hrowlxb.passy_backend.savedsite.dto.SavedSiteRequestDto;
import com.hrowlxb.passy_backend.savedsite.service.SavedSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/savedsites")
public class SavedSiteController {

    private final SavedSiteService savedSiteService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SavedSiteController(SavedSiteService savedSiteService, JwtTokenProvider jwtTokenProvider) {
        this.savedSiteService = savedSiteService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public SavedSite saveSavedSite(@RequestBody SavedSiteRequestDto savedSiteRequestDto,
                                   @RequestHeader("Authorization") String token) {

        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        return savedSiteService.saveSavedSite(email, savedSiteRequestDto);
    }

    @GetMapping
    public List<SavedSite> getSavedSites(@RequestHeader("Authorization") String token) {
        String email = jwtTokenProvider.getEmailFromToken(token.replace("Bearer ", ""));
        return savedSiteService.getSavedSites(email);
    }
}
