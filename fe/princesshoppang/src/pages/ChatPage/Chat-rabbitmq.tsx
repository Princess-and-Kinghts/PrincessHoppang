import { useRef, useState, useEffect, useContext } from 'react';
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
  // const { selectedMbti } = useContext(MbtiContext);
  const client = useRef<any>({});
  // const [chatList, setChatList] = useState<Chat[]>([]);
  // const [chat, setChat] = useState<string>('');
  // const [roomId, setRoomId] = useState<number>();
  // const userNickname = localStorage.getItem('nickname');
  // const [isMatch, setIsMatch] = useState<boolean>(false);
  // 고유한 ID를 발급하는 함수
  // const generateId = (() => {
  //   let id = 0;
  //   return () => {
  //     id += 1;
  //     return id;
  //   };
  // })();

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

  const subscribeAfterGetRoomId = (id: number) => {
    client.current.subscribe(`/sub/chat/match/${id}`, onMessageReceived);
  };

  const publish = () => {
    console.log("publish")
    if (!client.current.connected) return;

    client.current.publish({
      destination: `/pub/game`,
      body: JSON.stringify({
        type: 'match',
        channelId: "chaen",
        sender: "자바스크립트",
        data: "데이터 없음",
      }),
    });

  };

  // const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
  //   setChat(event.target.value);
  // };

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
    // client.current.subscribe(`/topic/chaen`, () => {
      console.log("subscribe")
    // });
  };

  // // 최초 렌더링시 실행
  // useEffect(() => {
  //   const subscribe = () => {
  //     client.current.subscribe(`/sub/chat/wait/${userId}`, (body) => {
  //       const watingRoomBody = JSON.parse(body.body) as WatingRoomBody;
  //       const { type, roomId: newRoomId } = watingRoomBody;

  //       if (type === 'open') {
  //         console.log('채팅 웨이팅 시작');
  //       }

  //       if (type === 'match') {
  //         console.log('매칭이 되었습니다!');
  //         subscribeAfterGetRoomId(newRoomId);
  //         setRoomId(newRoomId);
  //         setIsMatch(true);
  //       }
  //     });
  //   };

  //   const publishOnWait = () => {
  //     if (!client.current.connected) return;

  //     client.current.publish({
  //       destination: '/pub/chat/wait',
  //       body: JSON.stringify({
  //         type: 'open',
  //         userId,
  //         selectMbti: `${selectedMbti}`,
  //       }),
  //     });
  //   };

    

  //   connect();

  //   return () => disconnect();
  //   // eslint-disable-next-line react-hooks/exhaustive-deps
  // }, [userId]);

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