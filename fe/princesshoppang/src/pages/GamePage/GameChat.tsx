import { useEffect, useState } from "react";
import Colors from "../../styles/Colors";
import { css } from "@emotion/react";
import { useAtom, useAtomValue } from "jotai";

import {useWebSocket} from "../../utils/WebSocketContext";
import { myColorAtom, myNicknameAtom } from "../../store/GameState";
import { GameChat } from "../../types/ChatTypes";
import TheirChat from "../../components/TheirChat";
import MyChat from "../../components/MyChat";
import InputBox from "../../components/InputBox";
import { useParams } from "react-router-dom";


const GameChat = () => {
  const {channelId} = useParams();

  const {SUBSCRIBE, PUBLISH } = useWebSocket();

  // const userId = useAtomValue(userIdAtom);
  const myNickname = useAtomValue(myNicknameAtom);
  const myColor = useAtomValue(myColorAtom);

  const [messages, setMessages] = useState<GameChat[]>([]); 

  useEffect(() =>{
    console.log(channelId);
    console.log(myColor);
    subscribe();
    publish();
  },[]);
  
  const publish = () => {
    console.log("publish")
    
    PUBLISH(
      "/pub/game",
      JSON.stringify({
        type: 'ENTER',
        channelId,
        userId: 1,
        data: {},
      }),
    );

  };

  const subscribe = () => {
    SUBSCRIBE(`/topic/${channelId}`, onMessageReceived)
    console.log("subscribe")
  };

  // 구독한 곳에서 매세지가 왔을 때
  const onMessageReceived = (message: StompJs.Message) => {
    const messageBody = JSON.parse(message.body) as MessageBody;
    console.log(messageBody);
    if (messages.length > 0){
      const lastMessage: GameChat = messages[messages.length -1];
      console.log("lastMessage: ", messages[messages.length -1]);
  
      if(lastMessage.nickname === messageBody.data.nickname && messageBody.data.sentAt - lastMessage.sentAt <= 60 * 1000) {
        lastMessage.messages.push(messageBody.data.message);
        setMessages((prevMessages) => [...prevMessages.slice(0, -1), lastMessage]);
      } else {
        setMessages(prevMessages => [...prevMessages, newMessage(messageBody.type, messageBody.data.nickname, messageBody.data.message, messageBody.data.sentAt)]);
      }
    } else {
      setMessages(prevMessages => [...prevMessages, newMessage(messageBody.type, messageBody.data.nickname, messageBody.data.message, messageBody.data.sentAt)]);
    }
  }
  
    console.log(messages)
  // 메세지 생성
  const newMessage = (type: string, nickname: string, message: string, sentAt: number) => {
    const gameChat : GameChat = {type, nickname, messages: [message], sentAt}
    return gameChat;
  }
    

    return (
        <div css={gameChatWidth}>
          {
            messages.map((message) => {
              // 일반 채팅인데
              if (message.type == "TALK"){
                // 내가 보냈을 때
                if(message.nickname == myNickname) {
                  return (
                    <MyChat
                      color={myColor}
                      message={message}
                    />
                  )
                // 남이 보냈을 때
                } else {
                  return (
                    <TheirChat
                      type="game"
                      color={myColor}
                      message={message}
                    />
                  )
                }
              }
            })
          }
         
          <InputBox
            type="game"
            color={myColor}
          ></InputBox>
        </div>
      // </div>
    );
  };
  
  export default GameChat;
 

  const gameChatWidth= css`
    width: 60vw;

    @media (max-width: 767px) {
      width: 100vw;
    }
  `
  