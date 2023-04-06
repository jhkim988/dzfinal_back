package com.douzone.dzfinal.service;

import com.douzone.dzfinal.dto.ReceptionDTO;
import com.douzone.dzfinal.dto.WaitingDTO;
import com.douzone.dzfinal.entity.Reception;
import com.douzone.dzfinal.repository.ReceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionService {

	@Autowired
	private ReceptionRepository receptionRepository;
	@Autowired
	private MqttMessageService mqttMessageService;
	public List<Reception> receptionList() {
		return receptionRepository.receptionList();
	}
	
	public void updateReceptionInfo(Reception reception) {
		receptionRepository.updateReceptionInfo(reception);
	}
	
	public List<WaitingDTO.WaitingData> todayList() {
		return receptionRepository.todayList();
	}

	public int insertReception(Reception reception) {
		receptionRepository.insertReception(reception);
		int reception_id = reception.getReception_id();
		mqttMessageService.sendToWaiting("PUT", reception_id, "진료대기");
		return reception_id;
	}
	
	public void updateTodayReservationState(Reception reception) {
		receptionRepository.updateTodayReservationState(reception);
	}

	public ReceptionDTO.Detail detail(int reception_id) {
		return receptionRepository.detail(reception_id);
	}
}
