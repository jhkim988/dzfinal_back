package com.douzone.dzfinal.controller;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.dzfinal.dto.ClinicResponse;
import com.douzone.dzfinal.service.ClinicService;

@Validated
@RestController
@RequestMapping("/clinic")
public class ClinicController {

	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/{reception_id}")
	public ClinicResponse.PatientInfo getPatientInfo(@PathVariable("reception_id") @Digits(integer = 8, fraction = 0) @Min(1) int reception_id) {		
		return clinicService.getPatientInfo(reception_id);
	}
	
	@GetMapping("/disease/{type}/{keyword}")
	public List<ClinicResponse.Underlying> getDiseaseList(@PathVariable("type") String type, @PathVariable("keyword") String keyword) {
		return clinicService.getDiseaseList(type, keyword);
	}
	
	@GetMapping("/drug/{type}/{keyword}")
	public List<ClinicResponse.DrugTaking> getDrugList(@PathVariable("type") String type, @PathVariable("keyword") String keyword) {
		return clinicService.getDrugList(type, keyword);
	}
	
	@PostMapping("/disease")
	public void insertUnderlying(@RequestBody ClinicResponse.Disease paramData) {
		clinicService.insertUnderlying(paramData);
	}
	
	@DeleteMapping("/disease")
	public void deleteUnderlying(@RequestParam @Digits(integer = 8, fraction = 0) @Min(1) int patient_id,
			@RequestParam @Digits(integer = 8, fraction = 0) @Min(1) int disease_id) {
		clinicService.deleteUnderlying(patient_id, disease_id);
	}
	
	@PostMapping("/drug")
	public void insertDrugTaking(@RequestBody ClinicResponse.Drug paramData) {
		clinicService.insertDrugTaking(paramData);
	}
	
	@DeleteMapping("/drug")
	public void deleteDrugTaking(@RequestParam @Digits(integer = 8, fraction = 0) @Min(1) int patient_id,
			@RequestParam @Digits(integer = 8, fraction = 0) @Min(1) int drug_id) {
		clinicService.deleteDrugTaking(patient_id, drug_id);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
	    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/clinic")
	public boolean insertClinic(@RequestBody ClinicResponse.Clinic paramData) {
		try {
			clinicService.insertClinic(paramData);
			return true;
		} catch (Exception e) {
			throw new IllegalArgumentException("중복진료");
		}
	}
	
	@PutMapping("/clinic")
	public boolean updateClinic(@RequestBody ClinicResponse.Clinic paramData) {
		try {
			clinicService.updateClinic(paramData);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("/mri/{patient_id}/{currentPage}")
	public ClinicResponse.MriPage getMriList(
	    @PathVariable("patient_id") @Digits(integer = 8, fraction = 0) @Min(1) int patient_id, @PathVariable("currentPage") int currentPage) {		
		int amount = 10;
		
		int total = clinicService.getTotal(patient_id);
		ClinicResponse.Pagination pagination = new ClinicResponse.Pagination(currentPage, amount, total);
		ClinicResponse.MriPage mriPage = new ClinicResponse.MriPage(clinicService.getMriList(patient_id, pagination), pagination);
		
	  return mriPage;
	}
	
	@PostMapping("/mri/search")
	public ClinicResponse.MriPage getSearchMriList(@RequestBody ClinicResponse.SearchInfo searchInfo) {
		int amount = 10;
		
		int total = clinicService.getSearchTotal(searchInfo);
		ClinicResponse.Pagination pagination = new ClinicResponse.Pagination(searchInfo.getCurrentPage(), amount, total);
		ClinicResponse.MriPage searchPage = new ClinicResponse.MriPage(clinicService.getSearchMriList(searchInfo, pagination), pagination);

	    return searchPage;
	}
	
	@GetMapping("/medicalinfo/{reception_id}")
	public ClinicResponse.MedicalInfo getMedicalInfo(@PathVariable("reception_id") @Digits(integer = 8, fraction = 0) @Min(1) int reception_id) {
		return clinicService.getMedicalInfo(reception_id);
	}
}