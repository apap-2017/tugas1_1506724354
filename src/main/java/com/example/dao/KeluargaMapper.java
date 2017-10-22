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

@Mapper
public interface KeluargaMapper {
	@Select("SELECT alamat, rt, rw, id_kelurahan FROM (SELECT alamat, rt, rw, id_kelurahan, id FROM keluarga) AS keluarga WHERE id = #{id}")
	KeluargaModel selectAlamatKeluarga(@Param("id") int id);
	
	@Select("SELECT nomor_kk, alamat, rt, rw, id, id_kelurahan FROM (SELECT nomor_kk, alamat, rt, rw, id, id_kelurahan FROM keluarga) AS keluarga WHERE nomor_kk = #{nkk}")
	KeluargaModel selectAlamatNKKeluarga(@Param("nkk") String nkk);
	
	@Select("SELECT id FROM (SELECT id FROM keluarga) AS keluarga WHERE id = #{id_keluarga}")
	KeluargaModel selectIdKeluarga(@Param("id_keluarga") int id_keluarga);
	
	@Select("SELECT nomor_kk FROM (SELECT nomor_kk, id FROM keluarga) AS keluarga WHERE id = #{id_keluarga}")
	KeluargaModel selectNKK(@Param("id_keluarga") int id_keluarga);
	
	@Select("SELECT max(nomor_kk) FROM (SELECT nomor_kk FROM keluarga) AS keluarga WHERE nomor_kk LIKE CONCAT(#{nomor_kk}, '%')")
	String cekKesamaanNKK(@Param("nomor_kk") String nomor_kk);
	
	@Insert("INSERT INTO keluarga ( nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) " +
			" VALUES(#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, #{is_tidak_berlaku})")
			void addKeluarga(
					@Param("nomor_kk") String nomor_kk, 
					@Param("alamat") String alamat, 
					@Param("rt") String rt, 
					@Param("rw") String rw, 
					@Param("id_kelurahan") int id_kelurahan,
					@Param("is_tidak_berlaku") int is_tidak_berlaku);
	
	@Select("SELECT id_kelurahan FROM (SELECT id_kelurahan, id FROM keluarga) AS penduduk WHERE id = #{id}")
	int cekIdKelurahan(@Param("id") int id);
	
	@Update("UPDATE keluarga SET nomor_kk = #{nomor_kk}, alamat = #{alamat}, rt = #{rt}, rw = #{rw}," +
	" id_kelurahan = #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} WHERE id = #{id}")
	void updateKeluarga(@Param("id") int id,
			@Param("nomor_kk") String nomor_kk, 
			@Param("alamat") String alamat, 
			@Param("rt") String rt, 
			@Param("rw") String rw, 
			@Param("id_kelurahan") int id_kelurahan,
			@Param("is_tidak_berlaku") int is_tidak_berlaku);
	
	@Update("UPDATE keluarga SET is_tidak_berlaku = 1 WHERE id = #{id} ")
	void updateIsTidakBerlaku(@Param("id") int id);
}
