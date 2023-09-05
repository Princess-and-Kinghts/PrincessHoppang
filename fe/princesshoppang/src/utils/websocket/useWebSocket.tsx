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
      stompClient.subscribe("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", (message:any) => {
        const msg = JSON.parse(message.body)
        console.log("webSocketProvider", msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);
        const newMessage = JSON.parse(message.body);
    // setWebSocketMessages((prevMessages) => [...prevMessages, newMessage]);
    });
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
