package org.example.service;


import org.apache.ibatis.annotations.Param;

public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
