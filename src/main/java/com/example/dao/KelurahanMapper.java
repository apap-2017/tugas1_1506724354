package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Many;

import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {
	@Select("SELECT kelurahan.nama_kelurahan, id_kecamatan, id FROM (SELECT nama_kelurahan, id_kecamatan, id FROM kelurahan) AS kelurahan WHERE id = #{id}")
	KelurahanModel selectNamaKelurahan(@Param("id") int id);
	
	@Select("SELECT kelurahan.nama_kelurahan, id FROM (SELECT nama_kelurahan, id FROM kelurahan) AS kelurahan")
	List<KelurahanModel> selectAllKelurahan();
	
	@Select("SELECT kelurahan.nama_kelurahan, id FROM kelurahan WHERE id_kecamatan = #{id} ")
	List<KelurahanModel> selectKelurahanKecamatan(@Param("id") int id);
	
	@Select("SELECT kode_kelurahan FROM (SELECT kode_kelurahan, id FROM kelurahan) AS kelurahan WHERE id = #{id}")
	KelurahanModel selectKodeKelurahan(@Param("id") int id);
	
	@Select("SELECT id_kecamatan, kode_kelurahan FROM kelurahan WHERE id = #{id}")
	KelurahanModel cekIdKecamatan(@Param("id") int id);
}
