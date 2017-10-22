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

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper {	
	@Select("SELECT * FROM penduduk WHERE penduduk.nik = #{nik}")
	PendudukModel selectDataPenduduk(@Param("nik") String nik);
	
	@Select("SELECT nama, nik, jenis_kelamin, tempat_lahir, tanggal_lahir, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, is_wni FROM (SELECT nama, nik, jenis_kelamin, tempat_lahir, tanggal_lahir, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, is_wni, id_keluarga FROM penduduk) AS penduduk WHERE id_keluarga = #{id}")
	List<PendudukModel> selectDataPendudukNKK(@Param("id") int id);
	
	@Select("SELECT max(nik) FROM (SELECT nik FROM penduduk) AS penduduk WHERE nik LIKE CONCAT(#{nik}, '%')")
	String cekKesamaanNIK(@Param("nik") String nik);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) " +
	" VALUES(#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addPenduduk(
			@Param("nik") String nik, 
			@Param("nama") String nama, 
			@Param("tempat_lahir") String tempat_lahir, 
			@Param("tanggal_lahir") String tanggal_lahir, 
			@Param("jenis_kelamin") int jenis_kelamin,
			@Param("is_wni") int is_wni,
			@Param("id_keluarga") int id_keluarga,
			@Param("agama") String agama,
			@Param("pekerjaan") String pekerjaan,
			@Param("status_perkawinan") String status_perkawinan,
			@Param("status_dalam_keluarga") String status_dalam_keluarga,
			@Param("golongan_darah") String golongan_darah,
			@Param("is_wafat") int is_wafat);
	
	@Select("SELECT jenis_kelamin FROM (SELECT jenis_kelamin, nik FROM penduduk) AS penduduk WHERE nik = #{nik}")
	int cekJenisKelamin(@Param("nik") String nik);
	
	@Select("SELECT tanggal_lahir FROM (SELECT tanggal_lahir, nik FROM penduduk) AS penduduk WHERE nik = #{nik}")
	String cekTanggalLahir(@Param("nik") String nik);
	
	@Select("SELECT id_keluarga FROM (SELECT id_keluarga, nik FROM penduduk) AS penduduk WHERE nik = #{nik}")
	int cekIdKeluarga(@Param("nik") String nik);
	
	@Update("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}," +
	" jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}," +
	" status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} WHERE id = #{id}")
	void updatePendudukNIK(@Param("id") int id, @Param("nik") String nik, @Param("nama") String nama, @Param("tempat_lahir") String tempat_lahir,
			@Param("tanggal_lahir") String tanggal_lahir, @Param("jenis_kelamin") int jenis_kelamin, @Param("is_wni") int is_wni,
			@Param("id_keluarga") int id_keluarga, @Param("agama") String agama, 
			@Param("pekerjaan") String pekerjaan, @Param("status_perkawinan") String status_perkawinan, 
			@Param("status_dalam_keluarga") String status_dalam_keluarga, @Param("golongan_darah") String golongan_darah, 
			@Param("is_wafat") int is_wafat);
	
	@Update("UPDATE penduduk SET nama = #{nama}, tempat_lahir = #{tempat_lahir}," +
	" is_wni = #{is_wni}, id_keluarga = #{id_keluarga}, agama = #{agama}, pekerjaan = #{pekerjaan}," +
	" status_perkawinan = #{status_perkawinan}, status_dalam_keluarga = #{status_dalam_keluarga}, golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} " +
	" WHERE nik = #{nik}")
			void updatePenduduk(@Param("nik") String nik, @Param("nama") String nama, @Param("tempat_lahir") String tempat_lahir,
					@Param("is_wni") int is_wni, @Param("id_keluarga") int id_keluarga, @Param("agama") String agama, @Param("pekerjaan") String pekerjaan,
					@Param("status_perkawinan") String status_perkawinan, @Param("status_dalam_keluarga") String status_dalam_keluarga,
					@Param("golongan_darah") String golongan_darah, @Param("is_wafat") int is_wafat);
	
	@Select("SELECT nik FROM (SELECT nik, id_keluarga FROM penduduk) AS penduduk WHERE id_keluarga = #{id}")
	List<PendudukModel> selectNIKKeluarga(@Param("id") int id);
	
	@Update("UPDATE penduduk SET nik = #{nik} WHERE nik = #{nikLama}")
	void updateNIK(@Param("nikLama") String nikLama, @Param("nik") String nik);
	
	@Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
	void updateStatusKematian(@Param("nik") String nik);
	
	@Select("SELECT is_wafat FROM (SELECT is_wafat, id_keluarga FROM penduduk) AS penduduk WHERE id_keluarga = #{id}")
	List<PendudukModel> selectStatusKematian(@Param("id") int id);
	
	@Select("SELECT nik, nama, jenis_kelamin FROM penduduk JOIN keluarga ON penduduk.id_keluarga = keluarga.id WHERE keluarga.id_kelurahan = #{id}")
	List<PendudukModel> selectPendudukKelurahan(@Param("id") int id);
}
