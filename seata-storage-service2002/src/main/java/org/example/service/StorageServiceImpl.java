package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.StorageMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");

        storageMapper.decrease(productId,count);
        //int i=1/0;
        log.info("------->storage-service中扣减库存结束");
    }
}
