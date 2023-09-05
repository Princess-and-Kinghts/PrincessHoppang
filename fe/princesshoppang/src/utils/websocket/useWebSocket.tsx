// useWebSocket.ts
import { useEffect, useState } from 'react';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

function useWebSocket(url: string): Stomp.Client | null {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);

  useEffect(() => {
    const socket = new SockJS(url);
    const stomp = Stomp.over(socket);

    stomp.connect({}, () => {
      setStompClient(stomp);
    });

    return () => {
      if (stompClient) {
        stompClient.disconnect();
      }
    };
  }, [url]);

  return stompClient;
}

export default useWebSocket;
