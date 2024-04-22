package org.example.mapper;


import org.apache.ibatis.annotations.Param;
import org.example.entities.Storage;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {
    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}