package com.douzone.dzfinal.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douzone.dzfinal.dto.ClinicResponse;
import com.douzone.dzfinal.dto.ClinicResponse.SearchInfo;
import com.douzone.dzfinal.entity.Clinic;

@Repository
@Mapper
public interface ClinicRepository {
	Optional<ClinicResponse.PatientInfo> getPatientInfo(int reception_id);
	List<ClinicResponse.DrugTaking> getDrugTaking(@Param("patient_id") int patient_id);
	List<ClinicResponse.Underlying> getUnderlying(@Param("patient_id") int patient_id);
	
	List<ClinicResponse.Underlying> getDiseaseList(@Param("type") String type, @Param("keyword") String keyword);
	List<ClinicResponse.DrugTaking> getDrugList(@Param("type") String type, @Param("keyword") String keyword);
	void insertUnderlying(ClinicResponse.Disease paramData);
	void deleteUnderlying(@Param("patient_id") int patient_id, @Param("disease_id") int disease_id);
	void insertDrugTaking(ClinicResponse.Drug paramData);
	void deleteDrugTaking(@Param("patient_id") int patient_id, @Param("drug_id") int drug_id);
	
	void insertClinic(@Param("reception_id") int reception_id, @Param("symptom") String symptom, @Param("treatment") boolean treatment, @Param("clinic_request") boolean clinic_request, @Param("creator") int creator);
	void insertDiagnosis(@Param("reception_id") int reception_id, @Param("disease_ids") List<Integer> disease_ids, @Param("creator") int creator);
	void insertPrescription(@Param("reception_id") int reception_id, @Param("drug_ids") List<Integer> drug_ids);
	void updateClinic(@Param("reception_id") int reception_id, @Param("symptom") String symptom, @Param("treatment") boolean treatment, @Param("clinic_request") boolean clinic_request, @Param("updator") int updator);
	void deleteDiagnosis(@Param("reception_id") int reception_id);
	void deletePrescription(@Param("reception_id") int reception_id);
	
	List<ClinicResponse.MedicalRecordInquiry> getMriList(@Param("patient_id") int patient_id, @Param("pagination") ClinicResponse.Pagination pagination);
	List<ClinicResponse.DrugTaking> getDiagnosis(@Param("reception_id") int reception_id);
	List<ClinicResponse.Underlying> getPrescription(@Param("reception_id") int reception_id);
	
	ClinicResponse.MedicalInfo getMedicalInfo(int reception_id);
	List<ClinicResponse.MedicalRecordInquiry> getSearchMriList(@Param("searchInfo") ClinicResponse.SearchInfo searchInfo, @Param("pagination") ClinicResponse.Pagination pagination);
	int getTotal(@Digits(integer = 8, fraction = 0) @Min(1) int patient_id);
	int getSearchTotal(ClinicResponse.SearchInfo searchInfo);
	
	Optional<Clinic> findOneByReceptionId(int reception_id);
}