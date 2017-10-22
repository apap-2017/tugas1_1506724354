package com.example.model;

import java.util.List;

public class KelurahanModel {
	private int id;
    private String kode_kelurahan;
    private int id_kecamatan;
    private String nama_kelurahan;
    private String kode_pos;
    private List<KeluargaModel> keluargaKelurahan;
    
    public KelurahanModel(int id, String kode_kelurahan, int id_kecamatan, String nama_kelurahan, String kode_pos,
			List<KeluargaModel> keluargaKelurahan) {
		super();
		this.id = id;
		this.kode_kelurahan = kode_kelurahan;
		this.id_kecamatan = id_kecamatan;
		this.nama_kelurahan = nama_kelurahan;
		this.kode_pos = kode_pos;
		this.keluargaKelurahan = keluargaKelurahan;
	}


	public KelurahanModel() {

	}

	public List<KeluargaModel> getKeluargaKelurahan() {
		return keluargaKelurahan;
	}


	public void setKeluargaKelurahan(List<KeluargaModel> keluargaKelurahan) {
		this.keluargaKelurahan = keluargaKelurahan;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode_kelurahan() {
		return kode_kelurahan;
	}


	public void setKode_kelurahan(String kode_kelurahan) {
		this.kode_kelurahan = kode_kelurahan;
	}


	public int getId_kecamatan() {
		return id_kecamatan;
	}


	public void setId_kecamatan(int id_kecamatan) {
		this.id_kecamatan = id_kecamatan;
	}


	public String getNama_kelurahan() {
		return nama_kelurahan;
	}


	public void setNama_kelurahan(String nama_kelurahan) {
		this.nama_kelurahan = nama_kelurahan;
	}


	public String getKode_pos() {
		return kode_pos;
	}


	public void setKode_pos(String kode_pos) {
		this.kode_pos = kode_pos;
	}
    
    
}
