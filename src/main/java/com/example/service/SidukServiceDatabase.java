package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KecamatanMapper;
import com.example.dao.KeluargaMapper;
import com.example.dao.KelurahanMapper;
import com.example.dao.KotaMapper;
import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SidukServiceDatabase implements SidukService {
	
	@Autowired
    private PendudukMapper pendudukMapper;
	
	@Autowired
    private KeluargaMapper keluargaMapper;
	
	@Autowired
    private KelurahanMapper kelurahanMapper;
	
	@Autowired
    private KecamatanMapper kecamatanMapper;
	
	@Autowired
    private KotaMapper kotaMapper;
	
	@Override
    public PendudukModel selectDataPenduduk (String nik)
    {
        return pendudukMapper.selectDataPenduduk (nik);
    }
	
	public KeluargaModel selectAlamatKeluarga (int id) 
	{
		return keluargaMapper.selectAlamatKeluarga(id);
	}
	
	public KelurahanModel selectNamaKelurahan (int id) 
	{
		return kelurahanMapper.selectNamaKelurahan(id);
	}
	
	public KecamatanModel selectNamaKecamatan (int id) 
	{
		return kecamatanMapper.selectNamaKecamatan(id);
	}
	
	public KotaModel selectNamaKota (int id) 
	{
		return kotaMapper.selectNamaKota(id);
	}
	
	public KeluargaModel selectAlamatNKKeluarga(String nomor_kk) 
	{
		return keluargaMapper.selectAlamatNKKeluarga(nomor_kk);
	}
	
	public List<PendudukModel> selectDataPendudukNKK(int id) 
	{
		return pendudukMapper.selectDataPendudukNKK(id);
	}
	
	public KeluargaModel selectIdKeluarga(int id_keluarga) 
	{
		return keluargaMapper.selectIdKeluarga(id_keluarga);
	}
	
	public String cekKesamaanNIK(String nik)
	{
		return pendudukMapper.cekKesamaanNIK(nik);
	}
	
	public KeluargaModel selectNKK(int id_keluarga) 
	{
		return 	keluargaMapper.selectNKK(id_keluarga);
	}
	
	public void addPenduduk(String nik, String nama, String tempat_lahir, String tanggal_lahir, int jenis_kelamin,
			int is_wni, int id_keluarga, String agama, String pekerjaan, String status_perkawinan,
			String status_dalam_keluarga, String golongan_darah, int is_wafat) 
	{
		pendudukMapper.addPenduduk(nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga,
				agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat);
	}
	
	public List<KelurahanModel> selectAllKelurahan() {
		return kelurahanMapper.selectAllKelurahan();
	}
	
	public KelurahanModel selectKodeKelurahan(int id) {
		return kelurahanMapper.selectKodeKelurahan(id);
	}
	
	public String cekKesamaanNKK(String nomor_kk)
	{
		return keluargaMapper.cekKesamaanNKK(nomor_kk);
	}
	
	public void addKeluarga(String nomor_kk, String alamat, String rt, String rw, int id_kelurahan, int is_tidak_berlaku)
	{
		keluargaMapper.addKeluarga(nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku);
	}
	
	public int cekJenisKelamin(String nik) {
		return pendudukMapper.cekJenisKelamin(nik);
	}
	
	public String cekTanggalLahir(String nik) {
		return pendudukMapper.cekTanggalLahir(nik);
	}
	
	public int cekIdKeluarga(String nik) {
		return pendudukMapper.cekIdKeluarga(nik);
	}
	
	public int cekIdKelurahan(int id) {
		return keluargaMapper.cekIdKelurahan(id);
	}
	
	public KelurahanModel cekIdKecamatan(int id) {
		return kelurahanMapper.cekIdKecamatan(id);
	}
	
	public void updatePendudukNIK(int id, String nik, String nama, String tempat_lahir,
			String tanggal_lahir,  int jenis_kelamin, int is_wni,
			int id_keluarga, String agama, 
			String pekerjaan, String status_perkawinan, 
			String status_dalam_keluarga, String golongan_darah, 
			int is_wafat) {
		pendudukMapper.updatePendudukNIK(id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat);
	}
	
	public void updatePenduduk(String nik, String nama, String tempat_lahir,
			int is_wni, int id_keluarga, String agama,  String pekerjaan,
			String status_perkawinan, String status_dalam_keluarga,
			String golongan_darah, int is_wafat) {
		pendudukMapper.updatePenduduk(nik, nama, tempat_lahir, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat);
	}
	
	public void updateKeluarga(int id, String nomor_kk, String alamat, String rt, String rw, int id_kelurahan, 
			int is_tidak_berlaku) {
		keluargaMapper.updateKeluarga(id, nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku);
	}
	
	public List<PendudukModel> selectNIKKeluarga(int id) {
		return pendudukMapper.selectNIKKeluarga(id);
	}
	
	public void updateNIK(String nikLama, String nik){
		pendudukMapper.updateNIK(nikLama, nik);
	}
	
	public void updateStatusKematian(String nik) {
		pendudukMapper.updateStatusKematian(nik);
	}
	
	public List<PendudukModel> selectStatusKematian(int id){
		return pendudukMapper.selectStatusKematian(id);
	}
	
	public void updateIsTidakBerlaku(int id) {
		keluargaMapper.updateIsTidakBerlaku(id);
	}

	public List<KotaModel> selectAllKota(){
		return kotaMapper.selectAllKota();
	}
	
	public List<KecamatanModel> selectAllKecamatan(int id){
		return kecamatanMapper.selectAllKecamatan(id);
	}
	
	public List<PendudukModel> selectPendudukKelurahan(int id){
		return pendudukMapper.selectPendudukKelurahan(id);
	}

	public List<KelurahanModel> selectKelurahanKecamatan(int id){
		return kelurahanMapper.selectKelurahanKecamatan(id);
	}
}
