package com.biye.paper.biz.front;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FrontRepository {

    List<Map<String, String>> getNewMenu();

    List<Map<String, String>> getQueMenu();
}
