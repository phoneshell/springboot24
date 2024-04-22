package org.example.mapper;


import org.apache.ibatis.annotations.Param;
import org.example.entities.Account;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    /**
     * @param userId
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}