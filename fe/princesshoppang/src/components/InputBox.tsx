import { useState } from "react";
import inputboxStyles from "../styles/InputBoxStyles";
import vote from "../assets/game/vote.png";

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
    // 메세지 보내는 코드 넣기
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
        
        <textarea css={inputboxStyles["textarea"]} value={message} onChange={(e)=>{setMessage(e.target.value)}}/>
        <button css={message? inputboxStyles[color]: inputboxStyles["deactivated"]} type="submit">
          전송
        </button>
      </form>
    </div>
    
  );
};

export default InputBox;