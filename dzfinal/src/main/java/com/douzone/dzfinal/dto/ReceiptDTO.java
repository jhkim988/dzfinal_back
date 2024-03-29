package com.douzone.dzfinal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


public class ReceiptDTO {
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ReceptionInfo {
		// (Reception)접수
		private int reception_id;
		private int doctor;
		
		// (Patient)환자
		private int patient_id;
		private String patient_name;
		private boolean insurance;
		private String gender;
		private String front_registration_number;
		private String back_registration_number;
		private String address;
		private String detail_address;
		
		// (Clinic)진료
		private boolean treatment;
		private boolean clinic_request;
		
		// (Prescription)처방
		private boolean has_prescription;
	}
	
	
	
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetDisease {

		// (Reception)진료
		private int reception_id;

		// (Diagnosis)진단
		int diagnosis_id;
		
		// (Disease)질병
		private int disease_id;
		private String disease_code;
		private String disease_name;
		
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetDrug {
		
		// (Reception)진료
		private int reception_id;
		
		// (Prescription)처방
		private int prescription_id;
		
		// (Drug)약품
		private int drug_id;
		private String drug_code;
		private String drug_name;
		private String drug_standard;
		private String drug_unit;
		
		
	}
	

//	@Data
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Builder
//	public static class TreatmentInfo {
//		
//		// (Disease)질병
//		private String disease_code;
//		private String disease_name;
//		
//		// (Drug)약품
//		private String drug_code;
//		private String drug_name;
//		
//		// (Diagnosis)진단
//		private Timestamp created_at;
//		
//	}
//	
//
//	@Data
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@Builder
//	public static class ClinicRequestInfo {
//		// (Disease)질병
//		private String disease_code;
//		private String disease_name;
//		
//		// (Drug)약품
//		private String drug_code;
//		private String drug_name;
//		
//		// (Diagnosis)진단
//		private Timestamp created_at;
//	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Diagnosis {
		private int disease_id;
		private String disease_name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Prescription {
		private int drug_id;
		private String drug_name;
	}

	
	
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetReceiptList {
		// (Reception)접수
		private int reception_id;
		private int doctor;
		private String state;

		// (Patient)환자
		private int patient_id;
		private String patient_name;
		private String front_registration_number;
		private String phone_number3;
		
		// (Disease)질병
		private String disease_name;
		
		// (Drug)약품
		private String drug_name;
		
		// (Receipt)수납
		private String mode;
		private int total_amount;
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Timestamp created_at;
		
		private int receipt_id;
		private String employee_name;
		private List<Diagnosis> diagnosisList;
		private List<Prescription> prescriptionList;	
		
		private String type;
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Timestamp start_date;
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Timestamp end_date;
		private String searchText;
		
		private int disease_count;
		private int prescription_count;
		
		private int currentPage;
		private int totalCount;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class GetReceiptListRequest {
		private List<GetReceiptList> list;
		private int totalCount;
	}

}