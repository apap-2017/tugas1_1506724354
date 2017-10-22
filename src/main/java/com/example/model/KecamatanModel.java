package com.example.model;

import java.util.List;

public class KecamatanModel {
	private int id;
    private String kode_kecamatan;
    private int id_kota;
    private String nama_kecamatan;
    private List<KelurahanModel> kelurahanKecamatan;
    
	public KecamatanModel() {

	}
	
	public KecamatanModel(int id, String kode_kecamatan, int id_kota, String nama_kecamatan,
			List<KelurahanModel> kelurahanKecamatan) {
		super();
		this.id = id;
		this.kode_kecamatan = kode_kecamatan;
		this.id_kota = id_kota;
		this.nama_kecamatan = nama_kecamatan;
		this.kelurahanKecamatan = kelurahanKecamatan;
	}

	public List<KelurahanModel> getKelurahanKecamatan() {
		return kelurahanKecamatan;
	}

	public void setKelurahanKecamatan(List<KelurahanModel> kelurahanKecamatan) {
		this.kelurahanKecamatan = kelurahanKecamatan;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKode_kecamatan() {
		return kode_kecamatan;
	}
	public void setKode_kecamatan(String kode_kecamatan) {
		this.kode_kecamatan = kode_kecamatan;
	}
	public int getId_kota() {
		return id_kota;
	}
	public void setId_kota(int id_kota) {
		this.id_kota = id_kota;
	}
	public String getNama_kecamatan() {
		return nama_kecamatan;
	}
	public void setNama_kecamatan(String nama_kecamatan) {
		this.nama_kecamatan = nama_kecamatan;
	}
}
