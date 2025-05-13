package com.hrowlxb.passy_backend.savedsite.repository;

import com.hrowlxb.passy_backend.savedsite.domain.SavedSite;
import com.hrowlxb.passy_backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedSiteRepository extends JpaRepository<SavedSite, Long> {

    List<SavedSite> findByUser(User user);
}
