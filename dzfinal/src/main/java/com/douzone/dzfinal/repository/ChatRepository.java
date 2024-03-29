package com.douzone.dzfinal.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douzone.dzfinal.dto.ChatDTO;

@Repository
@Mapper
public interface ChatRepository {
	List<ChatDTO.ChatRoom> chatRoomList(int participants_id);
	List<ChatDTO.Thumbnail> getThumbnail();
	void insert(ChatDTO.Message chatMessage);
	List<ChatDTO.Message> getChatRoomMessages(@Param("chatroom_id") int participants_id, @Param("last") int lastChatId);
	List<ChatDTO.MessageCount> getMessageCount(int participants_id);
	List<Integer> getNotificationTargetIds(@Param("chatroom_id") int chatroom_id, @Param("participants_id") int participants_id);
	void updateLastReadTime(ChatDTO.ChatRoomStatus status);
	void is_Close(ChatDTO.ChatRoomStatus status);
}
