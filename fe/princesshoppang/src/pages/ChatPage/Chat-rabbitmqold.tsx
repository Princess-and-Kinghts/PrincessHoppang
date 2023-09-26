import { useRef } from 'react';
import { useParams } from 'react-router-dom';
import * as StompJs from '@stomp/stompjs';

interface Chat {
  id: number;
  content: string;
  isMine: boolean;
  time: string;
  nickname: string;
}

interface WatingRoomBody {
  type: string;
  roomId: number;
  sendUserId?: string;
  content?: string;
}

interface MessageBody {
  type: string;
  sendUserId: string;
  content: string;
  time: string;
  nickname: string;
}



const Chat = () => {
  const { userId } = useParams<{ userId: string }>();
  const client = useRef<any>({});


  const disconnect = () => {
    client.current.deactivate();
    console.log('채팅이 종료되었습니다.');
    // setIsMatch(false);
  };

  const onMessageReceived = (message: StompJs.Message) => {
    // 이게 백에서 설정한 Messge model
    const messageBody = JSON.parse(message.body) as MessageBody;
    console.log(messageBody);

    // const { type, sendUserId, content, time, nickname } = messageBody;
    // const isMine = sendUserId === userId;
    // const newChat = {
    //   id: generateId(),
    //   content,
    //   isMine,
    //   time,
    //   nickname,
    // };
    // console.log(newChat);
    // setChatList((prevChatList) => [...prevChatList, newChat]);
    // console.log(chatList);

    // if (type === 'close') {
    //   console.log('closed');
    // }
  };

  // const subscribeAfterGetRoomId = (id: number) => {
  //   client.current.subscribe(`/sub/chat/match/${id}`, onMessageReceived);
  // };

  const publish = () => {
    console.log("publish")
    if (!client.current.connected) return;

    client.current.publish({
      destination: `/pub/game`,
      body: JSON.stringify({
        type: 'TALK',
        channelId: "chaen",
        userId: "1",
        data: "데이터 없음???",
      }),
    });

  };


  const connect = () => {
    client.current = new StompJs.Client({
      brokerURL: 'ws://localhost:8081/ws',
      onConnect: () => {
        console.log('connect');
      },
    });
    client.current.activate();
  };

  const subscribe = () => {
    client.current.subscribe("/topic/chaen", onMessageReceived)
      console.log("subscribe")
  };

 

  

    



  return (
    <div>
      <button onClick={() => {connect()}}>connect</button>
      <br />
      <button onClick={() => {subscribe()}}>subscribe</button>
      <br />
      <button onClick={() => {publish()}}>publish</button>
      <br />
      <button onClick={() => {disconnect()}}>disconnect</button>
    </div>
  );
};
export default Chat;