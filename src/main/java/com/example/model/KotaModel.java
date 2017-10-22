package com.example.model;

import java.util.List;

public class KotaModel {
	private int id;
    private String kode_kota;
    private String nama_kota;
    private List<KecamatanModel> kecamatanKota;
	
    public KotaModel(int id, String kode_kota, String nama_kota, List<KecamatanModel> kecamatanKota) {
		super();
		this.id = id;
		this.kode_kota = kode_kota;
		this.nama_kota = nama_kota;
		this.kecamatanKota = kecamatanKota;
	}

	public KotaModel() {
    	
	}

	public List<KecamatanModel> getKecamatanKota() {
		return kecamatanKota;
	}

	public void setKecamatanKota(List<KecamatanModel> kecamatanKota) {
		this.kecamatanKota = kecamatanKota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode_kota() {
		return kode_kota;
	}

	public void setKode_kota(String kode_kota) {
		this.kode_kota = kode_kota;
	}

	public String getNama_kota() {
		return nama_kota;
	}

	public void setNama_kota(String nama_kota) {
		this.nama_kota = nama_kota;
	}
}