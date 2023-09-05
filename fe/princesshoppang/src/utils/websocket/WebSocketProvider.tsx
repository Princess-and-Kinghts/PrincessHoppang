// WebSocketProvider.js
import React, { createContext, useContext, useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import { useAtom } from 'jotai'; // Jotai 사용
import { webSocketMessagesState } from '../../store/store';

const WebSocketContext = createContext<any>(null);

export function useWebSocket() {
  return useContext(WebSocketContext);
}

export function WebSocketProvider({ children }: { children: React.ReactNode }) {
  const [stompClient, setStompClient] = useState<any>(null);
  const [webSocketMessages, setWebSocketMessages] = useAtom(webSocketMessagesState); // Jotai 상태

  useEffect(() => {
    const socket = new SockJS("http://127.0.0.1:8081/ws-stomp/");
    const stomp = Stomp.over(socket);

    stomp.connect({}, (frame) => {
        setStompClient(stomp);
    });

    // WebSocket에서 메시지를 받아 Jotai 상태에 추가
    if (stompClient) {
        stompClient.subscribe("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", (message:any) => {
            const msg = JSON.parse(message.body)
            console.log("webSocketProvider", msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);
            const newMessage = JSON.parse(message.body);
        // setWebSocketMessages((prevMessages) => [...prevMessages, newMessage]);
        });
    }

    return () => {
        if (stompClient) {
            stompClient.disconnect();
        }
    };
  }, []);

  const value = { stompClient, webSocketMessages };

  return (
    <WebSocketContext.Provider value={value}>
      {children}
    </WebSocketContext.Provider>
  );
}
