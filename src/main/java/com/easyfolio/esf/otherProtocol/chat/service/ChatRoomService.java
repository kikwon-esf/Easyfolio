package com.easyfolio.esf.otherProtocol.chat.service;

import com.easyfolio.esf.otherProtocol.chat.vo.ChatRoomVO;

import java.util.List;

public interface ChatRoomService {

    public int createChatRoom();
    public int deleteChatRoom();
    public List<ChatRoomVO> getChatRoomList();
}
