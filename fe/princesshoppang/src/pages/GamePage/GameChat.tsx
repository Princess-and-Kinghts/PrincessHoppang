import { useEffect, useState } from "react";
import Colors from "../../styles/Colors";
import { css } from "@emotion/react";
import { useAtom } from "jotai";
import { me } from "../../store/GameState";
import InputBox from "../../components/InputBox";


const GameChat = () => {
    const [themeColor,setThemeColor] = useAtom(me);

    const testColor = () => {
      // 익명 호빵에 맞추어 테마색 결정
      // 나중에는 이부분 websocket에서 가져오는 걸로 바꾸어주어야함
      setThemeColor(themeColor+1);
      console.log(themeColor)
    }

    const [message, setMessage] = useState("");
    const messageHandler = () => {
      setMessage()
    }
    const submitHandler = () => {
      
    }

    // useEffect(() => {
      
    // }, [])

    return (
      <div css={themeLight({themeColor})}>
        <h1 css>GameChat</h1>
        <InputBox
          color="red"
          onSubmit={submitHandler}
          onChange={messageHandler}
        ></InputBox>
        <button type="button" onClick={() =>{testColor()}}>색깔</button>
      </div>
    );
  };
  
  export default GameChat;
  
  const themeLight = (props: { themeColor: number; }) => css`
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

  const themeOriginal = (props: { themeColor: number; }) => css`
    background-color: ${props.themeColor === 1
      ? Colors.red.origin
      : props.themeColor === 2
      ? Colors.orange.origin
      : props.themeColor === 3
      ? Colors.yellow.origin
      : props.themeColor === 4
      ? Colors.green.origin
      : props.themeColor === 5
      ? Colors.blue.origin
      : props.themeColor === 6
      ? Colors.navy.origin
      : props.themeColor === 7
      ? Colors.purple.origin
      : "none"};
  `;