package com.example.model;



public class PendudukModel{
    private int id;
    private String nik;
    private String nama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private int jenis_kelamin;
    private int is_wni;
    private int id_keluarga;
    private String agama;
    private String pekerjaan;
    private String status_perkawinan;
    private String status_dalam_keluarga;
    private String golongan_darah;
    private int is_wafat;
    
    public PendudukModel() {
    	
    }
	
    
    public PendudukModel(int id, String nik, String nama, String tempat_lahir, String tanggal_lahir, int jenis_kelamin, int is_wni,
			int id_keluarga, String agama, String pekerjaan, String status_perkawinan,
			String status_dalam_keluarga, String golongan_darah, int is_wafat) {
		this.id = id;
		this.nik = nik;
		this.nama = nama;
		this.tempat_lahir = tempat_lahir;
		this.jenis_kelamin = jenis_kelamin;
		this.is_wni = is_wni;
		this.id_keluarga = id_keluarga;
		this.agama = agama;
		this.pekerjaan = pekerjaan;
		this.status_perkawinan = status_perkawinan;
		this.status_dalam_keluarga = status_dalam_keluarga;
		this.golongan_darah = golongan_darah;
		this.is_wafat = is_wafat;
	}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getTempat_lahir() {
		return tempat_lahir;
	}
	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}
	public String getTanggal_lahir() {
		return tanggal_lahir;
	}
	public void setTanggal_lahir(String tanggal_lahir) {
		this.tanggal_lahir = tanggal_lahir;
	}
	public int getJenis_kelamin() {
		return jenis_kelamin;
	}
	public void setJenis_kelamin(int jenis_kelamin) {
		this.jenis_kelamin = jenis_kelamin;
	}
	public int getIs_wni() {
		return is_wni;
	}
	public void setIs_wni(int is_wni) {
		this.is_wni = is_wni;
	}
	public int getId_keluarga() {
		return id_keluarga;
	}
	public void setId_keluarga(int id_keluarga) {
		this.id_keluarga = id_keluarga;
	}
	public String getAgama() {
		return agama;
	}
	public void setAgama(String agama) {
		this.agama = agama;
	}
	public String getPekerjaan() {
		return pekerjaan;
	}
	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}
	public String getStatus_perkawinan() {
		return status_perkawinan;
	}
	public void setStatus_perkawinan(String status_perkawinan) {
		this.status_perkawinan = status_perkawinan;
	}
	public String getStatus_dalam_keluarga() {
		return status_dalam_keluarga;
	}
	public void setStatus_dalam_keluarga(String status_dalam_keluarga) {
		this.status_dalam_keluarga = status_dalam_keluarga;
	}
	public String getGolongan_darah() {
		return golongan_darah;
	}
	public void setGolongan_darah(String golongan_darah) {
		this.golongan_darah = golongan_darah;
	}
	public int getIs_wafat() {
		return is_wafat;
	}
	public void setIs_wafat(int is_wafat) {
		this.is_wafat = is_wafat;
	}
}

