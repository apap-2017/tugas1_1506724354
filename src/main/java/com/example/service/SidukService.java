package com.example.service;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.model.KecamatanModel;
import com.example.model.KotaModel;

public interface SidukService {
	//Kebutuhan Fitur 1
	PendudukModel selectDataPenduduk (String nik);
	
	KeluargaModel selectAlamatKeluarga (int id);
	
	KelurahanModel selectNamaKelurahan (int id);
	
	KecamatanModel selectNamaKecamatan (int id);
	
	KotaModel selectNamaKota (int id);
	
	//Kebutuhan Fitur 2
	KeluargaModel selectAlamatNKKeluarga(String nomor_kk);
	
	List<PendudukModel> selectDataPendudukNKK(int id);
	
	//Kebutuhan Fitur 3
	KeluargaModel selectIdKeluarga(int id_keluarga);
	
	String cekKesamaanNIK(String nik);
	
	KeluargaModel selectNKK(int id_keluarga);
	
	void addPenduduk(String nik, String nama, String tempat_lahir, String tanggal_lahir, int jenis_kelamin,
			int is_wni, int id_keluarga, String agama, String pekerjaan, String status_perkawinan,
			String status_dalam_keluarga, String golongan_darah, int is_wafat);
	
	//Kebutuhan Fitur 4
	
	List<KelurahanModel> selectAllKelurahan();

	KelurahanModel selectKodeKelurahan(int id);
	
	String cekKesamaanNKK(String nomor_kk);
	
	void addKeluarga(String nomor_kk, String alamat, String rt, String rw, int id_kelurahan, int is_tidak_berlaku);
	
	//Kebutuhan Fitur 5
	
	int cekJenisKelamin(String nik);
	
	String cekTanggalLahir(String nik);
	
	int cekIdKeluarga(String nik);
	
	int cekIdKelurahan(int id);
	
	KelurahanModel cekIdKecamatan(int id);
	
	void updatePendudukNIK(int id, String nik, String nama, String tempat_lahir,
			String tanggal_lahir,  int jenis_kelamin, int is_wni,
			int id_keluarga, String agama, 
			String pekerjaan, String status_perkawinan, 
			String status_dalam_keluarga, String golongan_darah, 
			int is_wafat);
	
	void updatePenduduk(String nik,String nama, String tempat_lahir,
			int is_wni, int id_keluarga, String agama,  String pekerjaan,
			String status_perkawinan, String status_dalam_keluarga,
			String golongan_darah, int is_wafat);
	
	//Kebutuhan Fitur 6
	
	void updateKeluarga(int id, String nomor_kk, String alamat, String rt, String rw, int id_kelurahan, int is_tidak_berlaku);

	List<PendudukModel> selectNIKKeluarga(int id);
	
	void updateNIK(String nikLama, String nik);
	
	//Kebutuhan Fitur 7
	
	void updateStatusKematian(String nik);
	
	List<PendudukModel> selectStatusKematian(int id);
	
	void updateIsTidakBerlaku(int id);

	//Kebutuhan Fitur 8
	
	List<KotaModel> selectAllKota();
	
	List<KecamatanModel> selectAllKecamatan(int id);
	
	List<PendudukModel> selectPendudukKelurahan(int id);
	
	List<KelurahanModel> selectKelurahanKecamatan(int id);

}	
