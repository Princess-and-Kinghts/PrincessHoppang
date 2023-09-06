// WebSocketProvider.js
import React, { createContext, useContext, useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import Stomp from "stompjs";
// import Stomp from 'webstomp-client';
import { useAtom, useAtomValue } from 'jotai'; // Jotai 사용
import { webSocketConnectionState, webSocketMessagesState } from '../../store/store';
import { useWebSocketContext } from './WebSocketcontext';


const WebSocketContext = createContext<any>(null);

export function useWebSocket() {
  return useContext(WebSocketContext);
}

export function WebSocketProvider({ children }: { children: React.ReactNode }) {
  const { stompClient } = useWebSocketContext();

  // const [stompClient, setStompClient] = useState<any>(null);
  // const [stompClient, setStompClient] = useAtom<any>(stompClientAtom);
  // const [webSocketMessages, setWebSocketMessages] = useAtom(webSocketMessagesState); // Jotai 상태
  const [webSocketConnection, setWebSocketConnection] = useAtom(webSocketConnectionState);
  const check = useAtomValue(webSocketConnectionState);
  
  useEffect(() => {
    const socket = new SockJS("http://127.0.0.1:8081/ws-stomp/");
    const stomp = Stomp.over(socket);

    stomp.connect({}, (frame) => {
        // setStompClient(stomp);
        setWebSocketConnection(true);
        
        console.log("stompClient", stompClient);
        console.log("frame", frame);
        console.log("webSocketConnectionState", webSocketConnectionState);
        console.log("webSocketConnection",webSocketConnection);
    });
    console.log("stompClient", stompClient);
    console.log("webSocketConnectionState", webSocketConnectionState);
    console.log("webSocketConnection",webSocketConnection);
    // WebSocket에서 메시지를 받아 Jotai 상태에 추가
    // if (stompClient) {
    //     stompClient.subscribe("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", (message:any) => {
    //         const msg = JSON.parse(message.body)
    //         console.log("webSocketProvider", msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);
    //         setWebSocketConnection(true)
    //         // const newMessage = JSON.parse(message.body);
    //     // setWebSocketMessages((prevMessages) => [...prevMessages, newMessage]);
    //     });
    // }

    return () => {
        if (stompClient) {
            stompClient.disconnect();
        }
    };
  }, []);

  const value = { stompClient };

  return (
    <WebSocketContext.Provider value={value}>
      <p>
        {check.toString()}
      </p>
      {children}
    </WebSocketContext.Provider>
  );
}
