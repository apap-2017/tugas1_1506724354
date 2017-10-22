package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

@Mapper
public interface KecamatanMapper {
	@Select("SELECT kecamatan.nama_kecamatan, id, id_kota FROM (SELECT nama_kecamatan, id_kota, id FROM kecamatan) AS kecamatan WHERE id = #{id}")
	KecamatanModel selectNamaKecamatan(@Param("id") int id);
	
	@Select("SELECT nama_kecamatan, id FROM kecamatan WHERE id_kota = #{id}")
	List<KecamatanModel> selectAllKecamatan(@Param("id") int id);
}
