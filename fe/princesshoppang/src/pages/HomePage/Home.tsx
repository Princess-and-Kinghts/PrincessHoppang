import { useNavigate } from "react-router-dom";
import Button from "../../components/Button";
// import { useWebSocket } from "../../utils/websocket/WebSocketProvider";
import { ChangeEvent, SetStateAction, useState } from "react";
import { useAtom, useAtomValue, useSetAtom } from "jotai";
import { userIdAtom } from "../../store/UserState";
import { useWebSocket } from "../../utils/WebSocketContext";
import { channelId, channelIdAtom, matchAcceptedAtom, matchedAtom, matchingModalAtom, myColorAtom, myNicknameAtom, playersAtom, titleAtom } from "../../store/GameState";
import { Player } from "../../types/ChatTypes";

const Home = () => {
  const { PUBLISH, SUBSCRIBE } = useWebSocket();


  // const userId = useAtomValue(userIdAtom);
  const [userId, setUserId] = useAtom(userIdAtom);
  const setMatchingModal = useSetAtom(matchingModalAtom);
  const setMatched = useSetAtom(matchedAtom);
  const [ channelId, setChannelId ] = useAtom(channelIdAtom);
  const setTitle = useSetAtom(titleAtom);
  const setPlayers = useSetAtom(playersAtom);
  const setMyNickname = useSetAtom(myNicknameAtom);
  const setMyColor = useSetAtom(myColorAtom);



  // 게임 시작 버튼
  const startGame = () =>{
    console.log("startGame");

    startMatching();
    setMatchingModal(true);
    
    // if (userId < 7) {
    //   setUserId(userId + 1);
    // }
  };


  // 구독한 곳에서 매세지가 왔을 때
  const onMessageReceived = (message: StompJs.Message) => {
    const messageBody = JSON.parse(message.body) as MessageBody;
    console.log(messageBody);

    if (messageBody.type == "MATCHED") {
      
      if ( messageBody.data.players.some((player: { userId: any; }) => player.userId === userId)) {
        setMatched(true);
        setChannelId(messageBody.data.channelId); 
        setTitle(messageBody.data.title);
        setPlayers(messageBody.data.players);

        for(let i = 0; i < messageBody.data.players.length; i++){
          const player: Player = messageBody.data.players[i];

          if (player.userId == userId){
            
            setMyColor(player.color);

            if (player.color == "RED") {
              setMyNickname("딸기 호빵")
            } else if (player.color == "ORANGE") {
              setMyNickname("피자 호빵")
            } else if (player.color == "YELLOW") {
              setMyNickname("옥수수 호빵")
            } else if (player.color == "GREEN") {
              setMyNickname("야채 호빵")
            } else if (player.color == "BLUE") {
              setMyNickname("민트초코 호빵")
            } else if (player.color == "NAVY") {
              setMyNickname("블루베리 호빵")
            } else if (player.color == "PURPLE") {
              setMyNickname("자색고구마 호빵")
            }
            
            console.log("MATCHED:", messageBody.data.channelId);
            console.log("ChnnelId: ", channelId);
          };
        }
      }
    } 
  };

  const startMatching = () => {
    SUBSCRIBE("/topic/matching", onMessageReceived);
    PUBLISH("/pub/game", msg);
    console.log(msg)
  }

  var msg = JSON.stringify({
    type: "MATCH",
    channelId: "matching",
    userId: 3,
    data: "",
  });


  return (
    <div>
      <h1>Home</h1>
      <Button
          type="button"
          shapeType={"confirm"}
          onClick={() => { startGame() }}
        >
          게임 시작
      </Button>
    </div>
  );
};

export default Home;
