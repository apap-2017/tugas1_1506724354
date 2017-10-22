package com.example.model;

import java.util.List;

public class KeluargaModel {
	private int id;
    private String nomor_kk;
    private String alamat;
    private String rt;
    private String rw;
    private int id_kelurahan;
    private int is_tidak_berlaku;
    private List<PendudukModel> anggotaKeluarga;
    
	public KeluargaModel() {
		
	}

	public KeluargaModel(int id, String nomor_kk, String alamat, String rt, String rw, int id_kelurahan,
			int is_tidak_berlaku, List<PendudukModel> anggotaKeluarga) {
		this.id = id;
		this.nomor_kk = nomor_kk;
		this.alamat = alamat;
		this.rt = rt;
		this.rw = rw;
		this.id_kelurahan = id_kelurahan;
		this.is_tidak_berlaku = is_tidak_berlaku;
		this.anggotaKeluarga = anggotaKeluarga;
	}

	public List<PendudukModel> getAnggotaKeluarga() {
		return anggotaKeluarga;
	}

	public void setAnggotaKeluarga(List<PendudukModel> anggotaKeluarga) {
		this.anggotaKeluarga = anggotaKeluarga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomor_kk() {
		return nomor_kk;
	}

	public void setNomor_kk(String nomor_kk) {
		this.nomor_kk = nomor_kk;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public int getId_kelurahan() {
		return id_kelurahan;
	}

	public void setId_kelurahan(int id_kelurahan) {
		this.id_kelurahan = id_kelurahan;
	}

	public int getIs_tidak_berlaku() {
		return is_tidak_berlaku;
	}

	public void setIs_tidak_berlaku(int is_tidak_berlaku) {
		this.is_tidak_berlaku = is_tidak_berlaku;
	}
}
