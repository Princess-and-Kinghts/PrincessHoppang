import { ReactNode, createContext, useContext, useEffect, useRef } from 'react';
import * as StompJs from '@stomp/stompjs';

interface WebSocketContextValue {
  client: StompJs.Client | null;
}

const WebSocketContext = createContext<WebSocketContextValue | undefined>(undefined);
// const WebSocketContext = createContext();

export const useWebSocket = () => {
  return useContext(WebSocketContext);
};

const WebSocketProvider = ({ children }: { children?: ReactNode }) => {
  const client = useRef(null);

  useEffect(() => {
    client.current = new StompJs.Client({
      brokerURL: 'ws://localhost:8081/ws',
    });
    CONNECT();
;
    return () => {
      if (client.current.connected) {
        client.current.deactivate();
        console.log("DEACTIVATED!")
      }
    };
  }, []);

  const CONNECT = () => {
    if (!client.current.connected) {
      client.current.activate();
      console.log("CONNECTED!")
    }
  };

  const DISCONNECT = () => {
    if (client.current.connected) {
      client.current.deactivate();
    }
  };

  const SUBSCRIBE = (destination, callback) => {
    if (client.current.connected) {
      client.current.subscribe(destination, callback);
    }
  };

  const PUBLISH = (destination, message) => {
    if (client.current.connected) {
      client.current.publish({ destination, body: message });
    }
  };

  const value = {
    CONNECT,
    DISCONNECT,
    SUBSCRIBE,
    PUBLISH,
  };

  return (
    <WebSocketContext.Provider value={value}>
      {children}
    </WebSocketContext.Provider>
  );
};

export default WebSocketProvider;
