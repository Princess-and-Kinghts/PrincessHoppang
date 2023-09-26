import { FormEvent, useState } from "react";
import inputboxStyles from "../styles/InputBoxStyles";
import vote from "../assets/game/vote.png";
import {useWebSocket}  from "../utils/WebSocketContext";
import { useAtom, useAtomValue } from "jotai";
import { useParams } from "react-router-dom";
import { userIdAtom } from "../store/UserState";
import { myNicknameAtom } from "../store/GameState";
import { GameChat, GameChatRequest } from "../types/ChatTypes";

type InputBoxProps = {
    type: "game" | "chat";
    color: "deactivated" | "RED" | "ORANGE" | "YELLOW" | "GREEN" | "BLUE" | "NAVY" | "PURPLE";
};

const InputBox = ({
  type,
  color,
}: InputBoxProps) => {

  const {channelId} = useParams();

  const { PUBLISH } = useWebSocket();

  const userId = useAtomValue(userIdAtom);
  const myNickname = useAtomValue(myNicknameAtom);

  const [message, setMessage] = useState("");


  // 메세지 생성
  const newMessage = (message: string ) => {
    const gameChat : GameChatRequest = {nickname: myNickname, message, sentAt: Date.now()}
    return gameChat;
  }

  
  async function sendMessage() {
    var body = JSON.stringify({
      type: "TALK",
      channelId,
      userId: 2,
      data: newMessage(message)
    });

    PUBLISH("/pub/game", body)

    console.log("msg: ", message)
  }


  const submitHandler = (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    sendMessage();
    setMessage("")
  }


  return (
    // <button css={buttonStyles[shapeType]} type={type}>
    <div>
      <form css={inputboxStyles["outerContainer"]} action="submit" onSubmit={submitHandler}>
        {type == "game" ? 
          <div> 
            <img 
              css={inputboxStyles["vote"]}
              src={vote} 
              alt="vote" 
            /> 
          </div>
           : null
        }
        
        <textarea css={inputboxStyles["textarea"]} value={message} onChange={(e)=>{setMessage(e.target.value); console.log(message)}}/>
        <button css={message? inputboxStyles[color]: inputboxStyles["deactivated"]} type="submit">
          전송
        </button>
      </form>
    </div>
    
  );
};

export default InputBox;