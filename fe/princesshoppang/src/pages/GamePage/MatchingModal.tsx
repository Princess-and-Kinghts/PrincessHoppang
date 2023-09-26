import { useAtom, useAtomValue, useSetAtom } from "jotai";
import { channelIdAtom, introAtom, matchedAtom, matchingModalAtom } from "../../store/GameState";
import { useWebSocket } from "../../utils/WebSocketContext";
import Button from "../../components/Button";
import { userIdAtom } from "../../store/UserState";
import { useNavigate } from "react-router-dom";

const MatchingModal = () => {
  const navigate = useNavigate();

  const { PUBLISH, SUBSCRIBE } = useWebSocket();

  const setMatchingModal = useSetAtom(matchingModalAtom);
  const setMatched = useSetAtom(matchedAtom);
  const setIntro = useSetAtom(introAtom);

  const userId = useAtomValue(userIdAtom);
  const channelId = useAtomValue(channelIdAtom);
  const matched = useAtomValue(matchedAtom);

  const accept = () => {
    console.log("accept")
    SUBSCRIBE(`/topic/${channelId}`, onMessageReceived);
    PUBLISH("/pub/game", msg);
    console.log(msg);
    console.log(userId)
  }
  
  var msg = JSON.stringify({
      type: "ACCEPT",
      channelId,
      userId: 3,
      data: ""
  });

  const onMessageReceived = (message: StompJs.Message) => {
    const messageBody = JSON.parse(message.body) as MessageBody;
    console.log(messageBody);

    if (messageBody.type == "START") {
      // 게임으로 이동
      navigate(`/game/${channelId}`);
      // 게임 소개 화면 켜기
      setIntro(true);

      // 모달 끄기
      setMatchingModal(false);
      setMatched(false);
      
      // 게임 정보 저장
      
      // matching 구독 해제

    }    
  }

    return (
      <div>
        <h1>Matching</h1>
        {matched ? 
            <>
                <div>매칭 되었습니다. 게임을 수락하세요.</div>
                <Button
                    type="button"
                    shapeType={"confirm"}
                    key="0"
                    onClick={() => { accept(); } }
                >수락</Button>
            </>
        :
            <div>매칭 대기 중입니다. </div>
        }
      </div>
    );
  };
  
  export default MatchingModal;
  