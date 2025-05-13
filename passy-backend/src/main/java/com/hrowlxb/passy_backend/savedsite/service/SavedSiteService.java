package com.hrowlxb.passy_backend.savedsite.service;

import com.hrowlxb.passy_backend.savedsite.domain.SavedSite;
import com.hrowlxb.passy_backend.savedsite.dto.SavedSiteRequestDto;
import com.hrowlxb.passy_backend.savedsite.repository.SavedSiteRepository;
import com.hrowlxb.passy_backend.user.domain.User;
import com.hrowlxb.passy_backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SavedSiteService {

    private final SavedSiteRepository savedSiteRepository;
    private final UserRepository userRepository;

    @Autowired
    public SavedSiteService(SavedSiteRepository savedSiteRepository, UserRepository userRepository) {
        this.savedSiteRepository = savedSiteRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SavedSite saveSavedSite(String email, SavedSiteRequestDto savedSiteRequestDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        SavedSite savedSite = SavedSite.builder()
                .siteName(savedSiteRequestDto.getSiteName())
                .siteUrl(savedSiteRequestDto.getSiteUrl())
                .loginId(savedSiteRequestDto.getLoginId())
                .password(savedSiteRequestDto.getPassword())
                .user(user)
                .build();

        return savedSiteRepository.save(savedSite);
    }

    public List<SavedSite> getSavedSites(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return savedSiteRepository.findByUser(user);
    }
}
