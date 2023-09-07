// WebSocketProvider.tsx
import React, { createContext, useContext, useEffect, useState } from 'react';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

// WebSocket 컨텍스트 생성
const WebSocketContext = createContext<Stomp.Client | null>(null);

interface WebSocketProviderProps {
  children: React.ReactNode;
  url: string; // WebSocket 서버 URL
}

export const WebSocketProvider: React.FC<WebSocketProviderProps> = ({ children, url }) => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);

  useEffect(() => {
    // WebSocket 연결 설정
    const socket = new SockJS("http://127.0.0.1:8081/ws-stomp/");
    const stomp = Stomp.over(socket);

    stomp.connect({}, () => {
      console.log('WebSocket 연결 성공');
      setStompClient(stomp);
    });

    // 컴포넌트 언마운트 시 WebSocket 연결 해제
    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, [url]);

  return (
    <WebSocketContext.Provider value={stompClient}>
      {children}
    </WebSocketContext.Provider>
  );
};

export function useWebSocket() {
  const stompClient = useContext(WebSocketContext);

  if (!stompClient) {
    // throw new Error('useWebSocket must be used within a WebSocketProvider');
  }

  return stompClient;
}
