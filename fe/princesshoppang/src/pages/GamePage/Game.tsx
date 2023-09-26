import { useParams } from "react-router-dom";
import GameChat from "./GameChat";
import { useWebSocket } from "../../utils/WebSocketContext";
import { useEffect } from "react";
import { gameChatAtom, introAtom, myColorAtom, resultAtom } from "../../store/GameState";
import { useAtomValue } from "jotai";
import { css } from "@emotion/react";
import Colors from "../../styles/Colors";
import Intro from "./Intro";

const Game = () => {
  const channelId = useParams();

  const intro = useAtomValue(introAtom);
  const gameChat = useAtomValue(gameChatAtom);
  const result = useAtomValue(resultAtom);

  const myColor = useAtomValue(myColorAtom);

  useEffect(()=>{
    console.log("game");
    console.log(channelId);
  })

  return (
    <div css={themeLight({myColor})}>
      {/* <h1>Game</h1> */}
      
      {/* <div css={gameChatWidth}> */}
      { intro ? <Intro></Intro> : null}
      { gameChat ? <GameChat></GameChat> : null}

      {/* </div> */}
    </div>
  );
};

export default Game;

const gameChatWidth= css`
  width: 60vw;

  @media (max-width: 767px) {
    width: 100vw;
  }
`

const themeLight = (props: { myColor: string; }) => css`
    display: flex;
    justify-content: center;
    height: calc(100vh - 100px);
    background-color: ${props.myColor === "RED"
      ? Colors.red.light
      : props.myColor === "ORANGE"
      ? Colors.orange.light
      : props.myColor === "YELLOW"
      ? Colors.yellow.light
      : props.myColor === "GREEN"
      ? Colors.green.light
      : props.myColor === "BLUE"
      ? Colors.blue.light
      : props.myColor === "NAVY"
      ? Colors.navy.light
      : props.myColor === "PURPLE"
      ? Colors.purple.light
      : "none"};
  `;