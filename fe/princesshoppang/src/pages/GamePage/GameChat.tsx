import Colors from "../../styles/Colors";
import { css } from "@emotion/react";
import { useAtom } from "jotai";
import { me } from "../../store/GameState";
import InputBox from "../../components/InputBox";
import TheirChat from "../../components/TheirChat";
import MyChat from "../../components/MyChat";

const GameChat = () => {
    const messages = ["hello", "hi", "aksjflajdflkajsdflkjasd;lkfjasldkfja;slkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfdslkdjfd;alskjfa;lskdfj"]; 
    const [themeColor,setThemeColor] = useAtom(me);

    const testColor = () => {
      // 익명 호빵에 맞추어 테마색 결정
      // 나중에는 이부분 websocket에서 가져오는 걸로 바꾸어주어야함
      setThemeColor(themeColor+1);
      console.log(themeColor)
    }

    // useEffect(() => {
      
    // }, [])

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
            type="game"
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
