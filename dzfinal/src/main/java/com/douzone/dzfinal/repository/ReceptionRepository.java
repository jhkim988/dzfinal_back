package com.douzone.dzfinal.repository;

import com.douzone.dzfinal.entity.Reception;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceptionRepository {
	
	public List<Reception> receptionList();
	
	public int insertReception(Reception reception);
}
