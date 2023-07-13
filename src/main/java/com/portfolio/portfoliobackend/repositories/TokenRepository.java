package com.portfolio.portfoliobackend.repositories;

import com.portfolio.portfoliobackend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
    select t from Token t inner join App_user u on t.user.id = u.id
    where u.id = :userId and (t.expired = false or t.revoked = false)
    """)
    List<Token> findAllValidTokensByUser(Long userId);

    List<Token> findAllByUser(Long userId);

    Optional<Token> findByToken(String token);

    Optional<Token> findByRefreshTokenAndRefreshTokenRevokedIsFalse(String token);
}
