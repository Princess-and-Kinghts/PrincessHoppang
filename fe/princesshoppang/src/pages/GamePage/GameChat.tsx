import { useEffect, useState } from "react";
import Colors from "../../styles/Colors";
import { css } from "@emotion/react";
import { useAtom } from "jotai";

import InputBox from "../../components/InputBox";
import TheirChat from "../../components/TheirChat";
import MyChat from "../../components/MyChat";
import { useWebSocket } from "../../utils/websocket/WebSocketProvider";
import { me } from "../../store/GameState";
import { GameChat } from "../../types/ChatTypes";

const GameChat = () => {
    
    const [messages, setMessages] = useState<GameChat[]>([]); 
    const [themeColor,setThemeColor] = useAtom(me);

    const testColor = () => {
      // 익명 호빵에 맞추어 테마색 결정
      // 나중에는 이부분 websocket에서 가져오는 걸로 바꾸어주어야함
      setThemeColor(themeColor+1);
      console.log(themeColor)
    }

    // webSocket 사용
    let stompClient = useWebSocket();

    var header = { "AUTH": "test user" };

    var body = JSON.stringify({
      type: "TALK",
      roomId: "0fc97f82-46eb-4772-975b-ecb7a82038e2",
      sender: "nickname",
      message: "message",
      data: ""
    });

    useEffect(() => {
      if (stompClient) {  
        console.log("스톰프 클라이언트", stompClient)
        subscribeTopic();
  
      } else {
          console.log("스톰프 클라이언트 not connected", stompClient)
  
          stompClient?.connect(header, function (frame: any) {
            console.log("소켓 연결 성공", frame)
            subscribeTopic();
          })
      }
     
    }, []);
  
    const subscribeTopic = () => {
      console.log("구독")
      stompClient?.subscribe("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", function (message) {
        const msg = JSON.parse(message.body)
        const newMessage : GameChat = {nickname: "바보", message: msg.message}
        setMessages(prevMessages => [...prevMessages, newMessage]);

        console.log(msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);      
      
      }, header)
    }

    return (
      <div css={themeLight({themeColor})}>
        <div css={gameChatWidth}>
          {/* <h1 css>GameChat</h1> */}
          <TheirChat
            type="game"
            color="red"
            messages={messages}
          />
          <MyChat
            color="red"
            messages={messages}
          />
          <InputBox
            type="game"
            color="red"
          ></InputBox>
          <button type="button" onClick={() =>{testColor()}}>색깔</button>
        </div>
      </div>
    );
  };
  
  export default GameChat;
 
  const gameChatWidth= css`
    width: 60vw;

    @media (max-width: 767px) {
      width: 100vw;
    }
  `
  const themeLight = (props: { themeColor: number; }) => css`
    display: flex;
    justify-content: center;
    height: calc(100vh - 100px);
    background-color: ${props.themeColor === 1
      ? Colors.red.light
      : props.themeColor === 2
      ? Colors.orange.light
      : props.themeColor === 3
      ? Colors.yellow.light
      : props.themeColor === 4
      ? Colors.green.light
      : props.themeColor === 5
      ? Colors.blue.light
      : props.themeColor === 6
      ? Colors.navy.light
      : props.themeColor === 7
      ? Colors.purple.light
      : "none"};
  `;
