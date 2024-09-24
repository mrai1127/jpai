package com.rai.ecommerce_backend.repository;

import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
