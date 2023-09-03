import { ChangeEvent, FormEvent, useEffect, useState } from "react";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

import API from "../../utils/API";


const Chat = () => {

  const [nickname, setNickname] = useState("");

  const nicknameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    setNickname(event.target.value);
  }
  
  const [message, setmessage] = useState("");
  
  const messageChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    setmessage(event.target.value);
  };
  
  const [chat, setChat] = useState("")

  // websocket 통신

  var header = { "AUTH": "test user" };

  let socket = new SockJS("http://127.0.0.1:8081/ws-stomp/");
  let stompClient = Stomp.over(socket);

  useEffect(() => {
    stompClient.connect(header, function (frame) {
      console.log("소켓 연결 성공", frame)
      subscribeTopic();
    })
  }, []);

  var body = JSON.stringify({
    type: "TALK",
    roomId: "0fc97f82-46eb-4772-975b-ecb7a82038e2",
    sender: nickname,
    message: message,
  });
  
  const subscribeTopic = () => {
    console.log("구독")
    stompClient.subscribe("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", function (message) {
      const msg = JSON.parse(message.body)
      console.log(msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);
      setChat(msg.message);
    }, header)
  }

  const unSubscribeTopic = () => {
    console.log("구독 해제")
    stompClient.disconnect();
    // ("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2",header);
    // ("/sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", function (message) {
    //   const msg = JSON.parse(message.body)
    //   console.log(msg.sender, "로 부터 새로운 메시지 도착: ", msg.message);
    //   setChat(msg.message);
    // }, header)
  }

  // stompClient.subscribe("sub/chat/room/0fc97f82-46eb-4772-975b-ecb7a82038e2", function (message) {
  //   console.log("새로운 메시지 도착: " + message.body);
  // }, header);
  

  function sendMessage(event: FormEvent<HTMLFormElement>): void {
    event.preventDefault();
    stompClient.send("/pub/chat/message", body, header)
    console.log("msg: ", message)
  }
 

  return (
    <div>
      <h1>Chat</h1>
      <div>
        <div>{chat}</div>
      </div>
      <form action="submit" onSubmit={sendMessage}>
        <input 
          type="text" 
          onChange={nicknameChangeHandler}
        />
        <br />
        <input
          type="text"
          onChange={messageChangeHandler}
        />
        <button type="submit" >전송</button>
      </form>
      <button onClick={() => {subscribeTopic()}}>구독</button>
      <button onClick={() => {unSubscribeTopic()}}>구독 해제</button>
    </div>
  );
};

export default Chat;
