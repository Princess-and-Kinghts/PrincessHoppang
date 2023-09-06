import { ChangeEvent, useState } from "react";
import inputboxStyles from "../styles/InputBoxStyles";

type InputBoxProps = {
  // 스타일 추가한다면 여기도 추가 필요
  
  color: "deactivated" | "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
  onChange: () => void;
  onSubmit?: () => void;
};

const InputBox = ({
  // deviceType,
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
        <textarea css={inputboxStyles["textarea"]} value={message} onChange={(e)=>{setMessage(e.target.value)}}/>
        <button css={message? inputboxStyles[color]: inputboxStyles["deactivated"]} type="submit">
          전송
        </button>
      </form>
    </div>
    
  );
};

export default InputBox;