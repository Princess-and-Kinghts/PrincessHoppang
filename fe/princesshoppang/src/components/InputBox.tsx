import { FormEvent, useState } from "react";
import inputboxStyles from "../styles/InputBoxStyles";
import vote from "../assets/game/vote.png";
import { useWebSocket } from "../utils/websocket/WebSocketProvider";
import { useAtom, useAtomValue } from "jotai";
import { headerAtom } from "../store/WebSocketState";
import { me } from "../store/GameState";
import { send } from "vite";

type InputBoxProps = {
    type: "game" | "chat";
    color: "deactivated" | "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
};

const InputBox = ({
  type,
  color,
}: InputBoxProps) => {

  const [message, setMessage] = useState("");

  const submitHandler = (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    sendMessage();
    setMessage("")
  }
  
  let stompClient = useWebSocket();
  
  const header = useAtomValue(headerAtom);

  var body = JSON.stringify({
    type: "TALK",
    roomId: "0fc97f82-46eb-4772-975b-ecb7a82038e2",
    sender: "nickname",
    message: message,
    data: ""
  });

  async function sendMessage() {
    await stompClient?.send("/pub/game", header, body)
    console.log("msg: ", message)
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