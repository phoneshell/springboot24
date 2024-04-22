package org.example.service;

import org.example.entities.Pay;

import java.util.List;

public interface PayService {
    public int add(Pay pay);
    public int update(Pay pay);
    public int delete(Integer id);
    public Pay getById(Integer id);
    public List<Pay> getAll();
}
