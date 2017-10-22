package com.example.controller;	

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import com.example.service.SidukService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SidukController {
	
	@Autowired
	SidukService sidukDAO;
	 
	@RequestMapping(value = "/")
	public String index ()
	{
        return "index";
    }
	
	@RequestMapping("/penduduk")
	public String dataPenduduk (Model model, @RequestParam(value = "nik", required = false) String nik)
	{	
		PendudukModel pendudukModel = sidukDAO.selectDataPenduduk(nik);
		if(pendudukModel != null) {
			int id = pendudukModel.getId_keluarga();
			KeluargaModel keluargaModel = sidukDAO.selectAlamatKeluarga(id);
			if(keluargaModel != null) {
				id = keluargaModel.getId_kelurahan();
				KelurahanModel kelurahanModel = sidukDAO.selectNamaKelurahan(id);
				if(kelurahanModel != null){
					id = kelurahanModel.getId_kecamatan() ;
					KecamatanModel kecamatanModel = sidukDAO.selectNamaKecamatan(id);
					id = kecamatanModel.getId_kota();
					KotaModel kotaModel = sidukDAO.selectNamaKota(id);
					
					model.addAttribute ("pendudukModel", pendudukModel);
					model.addAttribute ("keluargaModel", keluargaModel);
					model.addAttribute ("kelurahanModel", kelurahanModel);
					model.addAttribute ("kecamatanModel", kecamatanModel);
					model.addAttribute ("kotaModel", kotaModel);
					
					return "data-penduduk";
				}
				else {
					return "error";
				}
			}
			else {
				return "error";
			}
		}
		else {
			return "error";
		}
	}
	
	@RequestMapping("/keluarga")
	public String dataKeluarga (Model model, @RequestParam(value = "nkk", required = false) String nkk)
	{
		KeluargaModel keluargaModel = sidukDAO.selectAlamatNKKeluarga(nkk);
		if(keluargaModel != null){
			int id = keluargaModel.getId();
			List<PendudukModel> pendudukModel = sidukDAO.selectDataPendudukNKK(id);
			keluargaModel.setAnggotaKeluarga(pendudukModel);
			id = keluargaModel.getId_kelurahan();
			KelurahanModel kelurahanModel = sidukDAO.selectNamaKelurahan(id);
			id = kelurahanModel.getId_kecamatan() ;
			KecamatanModel kecamatanModel = sidukDAO.selectNamaKecamatan(id);
			id = kecamatanModel.getId_kota();
			KotaModel kotaModel = sidukDAO.selectNamaKota(id);
			model.addAttribute ("keluargaModel", keluargaModel);
			model.addAttribute ("kelurahanModel", kelurahanModel);
			model.addAttribute ("kecamatanModel", kecamatanModel);
			model.addAttribute ("kotaModel", kotaModel);
	        return "data-keluarga";
		}
		else
		{
			return "erroran";
		}	
    }
	
	@RequestMapping(value = "/penduduk/tambah")
	public String tambahPendudukIsi (Model model, @ModelAttribute(value = "penduduk") PendudukModel penduduk)
	{
		return "tambah-penduduk";
    }
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String tambahPendudukValidasi (Model model, @ModelAttribute(value = "penduduk") PendudukModel penduduk)
	{
		int id_keluarga = penduduk.getId_keluarga();
		KeluargaModel keluarga = sidukDAO.selectIdKeluarga(id_keluarga);
		if(keluarga != null) {
			String nik;
			String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
			String tahun = tanggal_lahir[0].substring(2, 4);
			String bulan = tanggal_lahir[1];
			int hari = Integer.parseInt(tanggal_lahir[2]);
			if(penduduk.getJenis_kelamin() == 1) {
				hari = hari + 40;
			}
			KeluargaModel nkk = sidukDAO.selectNKK(id_keluarga);
			String nkkCutted = nkk.getNomor_kk().substring(0,6);
			nik = nkkCutted + hari + bulan + tahun;
			String cekMaxNIK = sidukDAO.cekKesamaanNIK(nik);
			if(cekMaxNIK == null) {
				nik = nkkCutted + hari + bulan + tahun + "0001" ;
			}
			else {
				long newNik = Long.parseLong(cekMaxNIK) + 1;
				nik = newNik + "";
			}
			sidukDAO.addPenduduk(nik, penduduk.getNama(), penduduk.getTempat_lahir(),
					penduduk.getTanggal_lahir(), penduduk.getJenis_kelamin(), penduduk.getIs_wni(), id_keluarga,
					penduduk.getAgama(), penduduk.getPekerjaan(), penduduk.getStatus_perkawinan(), penduduk.getStatus_dalam_keluarga(),
					penduduk.getGolongan_darah(), penduduk.getIs_wafat());
			penduduk.setNik(nik);
			model.addAttribute ("penduduk", penduduk);
			return "tambah-penduduk-sukses";
		}
		else return "";
    }
	
	@RequestMapping(value = "/keluarga/tambah")
	public String tambahKeluargaView (Model model, @ModelAttribute(value = "keluarga") KeluargaModel keluarga)
	{
		List<KelurahanModel> kelurahan = sidukDAO.selectAllKelurahan();
		model.addAttribute("kelurahan" ,kelurahan);
		return "tambah-keluarga";
    }
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String tambahKeluarga (Model model, @ModelAttribute(value = "keluarga") KeluargaModel keluarga,
			String year, String month, String day)
	{	
		KelurahanModel nomorKelurahan = sidukDAO.selectKodeKelurahan(keluarga.getId_kelurahan());
		String nomorKelurahanCutted = nomorKelurahan.getKode_kelurahan().substring(0,6);
		String nomor_kk = nomorKelurahanCutted + day + month + year; 
		String cekMaxNKK = sidukDAO.cekKesamaanNKK(nomor_kk);
		if(cekMaxNKK == null) {
			nomor_kk = nomor_kk + "0001" ;
		}
		else {
			long newNKK = Long.parseLong(cekMaxNKK) + 1;
			nomor_kk = newNKK + "";
		}
		sidukDAO.addKeluarga(nomor_kk, keluarga.getAlamat(), keluarga.getRt(), keluarga.getRw(), keluarga.getId_kelurahan(), 0);
		keluarga.setNomor_kk(nomor_kk);
		model.addAttribute("keluarga", keluarga);
		return "tambah-keluarga-sukses";
    }
	
	@RequestMapping(value = "/penduduk/ubah/{nik}")
	public String ubahPendudukIsi(Model model, @PathVariable(value = "nik") String nik)
	{
		PendudukModel penduduk = sidukDAO.selectDataPenduduk(nik);
		if(penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "ubah-penduduk";
		}
		else {
			return "error";
		}
    }
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String ubahPenduduk(Model model, @PathVariable(value = "nik") String nik, @ModelAttribute(value = "penduduk") PendudukModel penduduk)
	{
		int id_keluarga = penduduk.getId_keluarga();
		KeluargaModel keluarga = sidukDAO.selectIdKeluarga(id_keluarga);
		if(keluarga != null) {
			String[] tanggal_lahir = penduduk.getTanggal_lahir().split("-");
			String tahun = tanggal_lahir[0].substring(2, 4);
			String bulan = tanggal_lahir[1];
			int hari = Integer.parseInt(tanggal_lahir[2]);
			
			KeluargaModel nkk = sidukDAO.selectNKK(id_keluarga);
			String nkkCutted = nkk.getNomor_kk().substring(0,6);
			String nikLama = nik;
			
			int cekJenisKelamin = sidukDAO.cekJenisKelamin(nik);
			String cekTanggalLahir = sidukDAO.cekTanggalLahir(nik);
			int cekIdKeluarga = sidukDAO.cekIdKeluarga(nik);
			int cekIdKelurahan = sidukDAO.cekIdKelurahan(cekIdKeluarga);
			int cekIdKelurahanLama = sidukDAO.cekIdKelurahan(id_keluarga);
			KelurahanModel cekIdKecamatan = sidukDAO.cekIdKecamatan(cekIdKelurahan);
			KelurahanModel cekIdKecamatanLama = sidukDAO.cekIdKecamatan(cekIdKelurahanLama);
			
			if(!(cekTanggalLahir.equals(penduduk.getTanggal_lahir()))) {
				tanggal_lahir = cekTanggalLahir.split("-");
				tahun = tanggal_lahir[0].substring(2, 4);
				bulan = tanggal_lahir[1];
				hari = Integer.parseInt(tanggal_lahir[2]);
			}
			if(cekJenisKelamin != penduduk.getJenis_kelamin() ) {
				if(penduduk.getJenis_kelamin() == 1) {
					hari = hari + 40;
				}
			}
			if(cekIdKecamatan.getId_kecamatan() != cekIdKecamatanLama.getId_kecamatan()) {
				nkk = sidukDAO.selectNKK(cekIdKeluarga);
				nkkCutted = nkk.getNomor_kk().substring(0,6);
			}
			if(!(cekTanggalLahir.equals(penduduk.getTanggal_lahir())) || 
					cekJenisKelamin != penduduk.getJenis_kelamin() || 
					cekIdKecamatan.getId_kecamatan() != cekIdKecamatanLama.getId_kecamatan()) {
				nik = nkkCutted + hari + bulan + tahun;
				String cekMaxNIK = sidukDAO.cekKesamaanNIK(nik);
				if(cekMaxNIK == null) {
					nik = nkkCutted + hari + bulan + tahun + "0001";
				}
				else {
					long newNik = Long.parseLong(cekMaxNIK) + 1;
					nik = newNik + "";
				}				
				sidukDAO.updatePendudukNIK(penduduk.getId(), nik, penduduk.getNama(), penduduk.getTempat_lahir(), penduduk.getTanggal_lahir(), 
						penduduk.getJenis_kelamin(), penduduk.getIs_wni(), penduduk.getId_keluarga(), penduduk.getAgama(), 
						penduduk.getPekerjaan(), penduduk.getStatus_perkawinan(), penduduk.getStatus_dalam_keluarga(), 
						penduduk.getGolongan_darah(), penduduk.getIs_wafat());
			}
			else {
				sidukDAO.updatePenduduk(penduduk.getNik(), penduduk.getNama(), penduduk.getTempat_lahir(), penduduk.getIs_wni(),
						penduduk.getId_keluarga(), penduduk.getAgama(), 
						penduduk.getPekerjaan(), penduduk.getStatus_perkawinan(), penduduk.getStatus_dalam_keluarga(), 
						penduduk.getGolongan_darah(), penduduk.getIs_wafat());
			}
			penduduk.setNik(nikLama);
			model.addAttribute ("penduduk", penduduk);
			return "ubah-penduduk-sukses";
		}
		return "";
    }
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}")
	public String ubahKeluargaIsi(Model model, @PathVariable(value = "nkk") String nkk)
	{
		KeluargaModel keluarga = sidukDAO.selectAlamatNKKeluarga(nkk);
		if(keluarga != null) {
			List<KelurahanModel> allKelurahan = sidukDAO.selectAllKelurahan();
			model.addAttribute("allKelurahan" , allKelurahan);
			model.addAttribute("keluarga", keluarga);
			return "ubah-keluarga";
		}
		else {
			return "error";
		}
    }
	
	@RequestMapping(value = "/keluarga/ubah/{nkk}",  method = RequestMethod.POST)
	public String ubahKeluarga(Model model, @PathVariable(value = "nkk") String nkk,  
			@ModelAttribute(value = "keluarga") KeluargaModel keluarga, String year, String month, String day)
	{	
		String nomor_kkLama = nkk;
		KelurahanModel nomorKelurahan = sidukDAO.selectKodeKelurahan(keluarga.getId_kelurahan());
		String nomorKelurahanCutted = nomorKelurahan.getKode_kelurahan().substring(0,6);
		String nomor_kk = nomorKelurahanCutted + day + month + year;
		if(!(nkk.substring(0, 12).equals(nomor_kk))) {
			String cekMaxNKK = sidukDAO.cekKesamaanNKK(nomor_kk);
			if(cekMaxNKK == null) {
				nomor_kk = nomor_kk + "0001" ;
			}
			else {
				long newNKK = Long.parseLong(cekMaxNKK) + 1;
				nomor_kk = newNKK + "";
			}
			
			sidukDAO.updateKeluarga(keluarga.getId() , nomor_kk, keluarga.getAlamat(), keluarga.getRt(), keluarga.getRw(), keluarga.getId_kelurahan(), 0);
			keluarga.setNomor_kk(nomor_kkLama);
			model.addAttribute("keluarga", keluarga);
			
			List<PendudukModel> pendudukModel = sidukDAO.selectNIKKeluarga(keluarga.getId());
			for(int i = 0; i < pendudukModel.size(); i++) {
				String nikAnggotaLama = pendudukModel.get(i).getNik();
				pendudukModel.get(i).setNik(nomorKelurahanCutted + nikAnggotaLama.substring(6, 12));
				String cekMaxNIK = sidukDAO.cekKesamaanNIK(nomorKelurahanCutted + nikAnggotaLama.substring(6, 12));
				if(cekMaxNIK == null) {
					System.out.println(nomorKelurahanCutted + nikAnggotaLama.substring(6, 12));
					cekMaxNIK = nomorKelurahanCutted + nikAnggotaLama.substring(6, 12) + "0001";
					sidukDAO.updateNIK(nikAnggotaLama, cekMaxNIK);
				}
				else {
					long newNik = Long.parseLong(cekMaxNIK) + 1;
					cekMaxNIK = newNik + "";
					sidukDAO.updateNIK(nikAnggotaLama, cekMaxNIK);

				}
			}
		}
		else {
			sidukDAO.updateKeluarga(keluarga.getId() , nomor_kkLama, keluarga.getAlamat(), keluarga.getRt(), keluarga.getRw(), keluarga.getId_kelurahan(), 0);
			keluarga.setNomor_kk(nomor_kkLama);
			model.addAttribute("keluarga", keluarga);
		}
		return "tambah-keluarga-sukses";
    }
	
	@RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	public String ubahStatusPenduduk(Model model, @RequestParam(value = "nik", required = false) String nik, @RequestParam(value = "id_keluarga", required = false) int id_keluarga)
	{
		sidukDAO.updateStatusKematian(nik);
		model.addAttribute("nik",nik);
		KeluargaModel keluarga = sidukDAO.selectIdKeluarga(id_keluarga);
		List<PendudukModel> pendudukModel = sidukDAO.selectStatusKematian(keluarga.getId());
        int countMati = 0;
		for(int i = 0; i < pendudukModel.size(); i++) {
			if(pendudukModel.get(i).getIs_wafat() == 1) {
				countMati++;
			}
		}
        if(countMati == pendudukModel.size()){
        	sidukDAO.updateIsTidakBerlaku(keluarga.getId());
        }
        return "ubah-status-kematian-sukses";
    }
	
	@RequestMapping("/penduduk/cari")
	public String tampilKota(Model model, @RequestParam(value = "kt", required = false) String kt, 
			@RequestParam(value = "kc", required = false) String kc,
			@RequestParam(value = "kl", required = false) String kl)
	{
		if(kt == null) {
			List<KotaModel> allKota = sidukDAO.selectAllKota();
			model.addAttribute("allKota", allKota);
			return "cari-penduduk";
		} 
		else{
			if(kc != null) {
				if(kl != null) {
					int klInt = Integer.parseInt(kl);
					int kcInt = Integer.parseInt(kc);
					int ktInt = Integer.parseInt(kt);
					KelurahanModel allKelurahan = sidukDAO.selectNamaKelurahan(klInt);
					model.addAttribute("allKelurahan" , allKelurahan);
					KecamatanModel allKecamatan = sidukDAO.selectNamaKecamatan(kcInt);
					model.addAttribute("allKecamatan", allKecamatan);
					KotaModel allKota = sidukDAO.selectNamaKota(ktInt);
					model.addAttribute("allKota", allKota);
					List<PendudukModel> pendudukModel = sidukDAO.selectPendudukKelurahan(klInt);
					model.addAttribute("pendudukModel", pendudukModel);
					return "cari-anggota-penduduk";
				}
				else {
					int kcInt = Integer.parseInt(kc);
					int ktInt = Integer.parseInt(kt);
					List<KelurahanModel> allKelurahan = sidukDAO.selectKelurahanKecamatan(kcInt);
					model.addAttribute("allKelurahan" , allKelurahan);
					KecamatanModel allKecamatan = sidukDAO.selectNamaKecamatan(kcInt);
					model.addAttribute("allKecamatan", allKecamatan);
					KotaModel allKota = sidukDAO.selectNamaKota(ktInt);
					model.addAttribute("allKota", allKota);
					return "cari-penduduk-kelurahan";
				}
			}
			else {
				int ktInt = Integer.parseInt(kt);
				List<KecamatanModel> allKecamatan = sidukDAO.selectAllKecamatan(ktInt);
				model.addAttribute("allKecamatan", allKecamatan);
				KotaModel allKota = sidukDAO.selectNamaKota(ktInt);
				model.addAttribute("allKota", allKota);
				return "cari-penduduk-kecamatan";
			}
		}
    }

}