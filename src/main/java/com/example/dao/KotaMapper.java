package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KotaModel;

@Mapper
public interface KotaMapper {
	@Select("SELECT nama_kota, id FROM kota WHERE id = #{id}")
	KotaModel selectNamaKota(@Param("id") int id);
	
	@Select("SELECT nama_kota, id FROM kota")
	List<KotaModel> selectAllKota();
}
