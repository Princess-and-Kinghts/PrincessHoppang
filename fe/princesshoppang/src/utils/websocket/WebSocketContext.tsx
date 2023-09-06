// WebSocketContext.ts
import { createContext, useContext } from 'react';
import Stomp from 'webstomp-client';


interface WebSocketContextValue {
  stompClient: Stomp.Client | null;
}

export const WebSocketContext = createContext<WebSocketContextValue | undefined>(undefined);

export function useWebSocketContext() {
  const context = useContext(WebSocketContext);
  if (!context) {
    throw new Error('useWebSocketContext must be used within a WebSocketProvider');
  }
  return context;
}
