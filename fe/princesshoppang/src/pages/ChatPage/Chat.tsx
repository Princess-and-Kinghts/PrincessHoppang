import { useRef } from 'react';
import { useParams } from 'react-router-dom';
import * as StompJs from '@stomp/stompjs';
import { useWebSocket } from '../../utils/WebSocketContext';



const Chat = () => {
  const { userId } = useParams<{ userId: string }>();
  const {CONNECT, DISCONNECT, SUBSCRIBE, PUBLISH } = useWebSocket();


  
  const onMessageReceived = (message: StompJs.Message) => {
    // 이게 백에서 설정한 Messge model
    const messageBody = JSON.parse(message.body) as MessageBody;
    console.log(messageBody);
  };

  // const subscribeAfterGetRoomId = (id: number) => {
  //   client.current.subscribe(`/sub/chat/match/${id}`, onMessageReceived);
  // };

  const publish = () => {
    console.log("publish")

    PUBLISH(
      "/pub/game",
      JSON.stringify({
        type: 'TALK',
        channelId: "chaen",
        userId: "2",
        data: "데이터 없음???",
      }),
    );

  };



  const subscribe = () => {
    SUBSCRIBE("/topic/chaen", onMessageReceived)
    console.log("subscribe")
  };

 

  

    



  return (
    <div>
      <button onClick={CONNECT}>connect</button>
      <br />
      <button onClick={() => {subscribe()}}>subscribe</button>
      <br />
      <button onClick={() => {publish()}}>publish</button>
      <br />
      <button onClick={DISCONNECT}>disconnect</button>
    </div>
  );
};
export default Chat;