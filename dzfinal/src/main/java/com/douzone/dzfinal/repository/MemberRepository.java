package com.douzone.dzfinal.repository;

import com.douzone.dzfinal.dto.RegisterRequest;
import com.douzone.dzfinal.entity.Member;

//@Mapper
public interface MemberRepository {
	boolean insert(RegisterRequest RegisterRequest);
	Member findOneById(String id);
}
