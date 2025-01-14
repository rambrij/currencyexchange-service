package com.currencyexchange.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currencyexchange.moldel.UserInfo;

/**
 * @author Ram Brij
 * This class is used to store data into h2 db
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByname(String name); // Use 'email' if that is the correct field for login

}