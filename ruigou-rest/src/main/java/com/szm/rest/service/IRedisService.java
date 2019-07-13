package com.szm.rest.service;

import com.szm.pojo.RuigouResult;

public interface IRedisService {

    RuigouResult syncContent(long contentCid);
}
