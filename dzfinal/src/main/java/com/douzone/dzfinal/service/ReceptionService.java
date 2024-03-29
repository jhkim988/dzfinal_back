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

		WaitingDTO.WaitingData waitingData = receptionRepository
				.findReceptionInfoById(reception.getReception_id())
				.orElseThrow(() -> new IllegalArgumentException("No reception_id"));
		waitingData.setState("진료대기");

		mqttMessageService.sendToWaiting(WaitingDTO.builder()
				.method("ADD")
				.data(waitingData)
				.build());

		return reception.getReception_id();
	}
	
	public void updateTodayReservationState(Reception reception) {
		receptionRepository.updateTodayReservationState(reception);
	}

	public void deleteReception(Reception reception) {
		System.out.println(reception);
		receptionRepository.deleteReception(reception);
		mqttMessageService.deleteWaitingState(reception.getReception_id());
	}
	
	public ReceptionDTO.Detail detail(int reception_id) {
		return receptionRepository.detail(reception_id);
	}
}
