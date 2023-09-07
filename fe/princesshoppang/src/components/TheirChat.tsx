import { useEffect, useState } from "react";
import red from "../assets/game/red.jpg";
import { theirChatStyles } from "../styles/TheirChatStyles";

type TheirChatProps = {
    type: "game" | "chat";
    messages?: string[];
    color: "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
    sentAt?: String;
};

const TheirChat = ({
    type,
    messages,
    color,
    sentAt
}: TheirChatProps) => {
    const [nickname, setNickname] = useState("")

    useEffect(()=>{
        if ( type == "chat") {
            setNickname("진짜 닉네임")
        } else {
            if (color == "red") {
                setNickname("딸기 호빵")
            } else if (color == "orange") {
                setNickname("피자 호빵")
            } else if (color == "yellow") {
                setNickname("옥수수 호빵")
            } else if (color == "green") {
                setNickname("야채 호빵")
            } else if (color == "blue") {
                setNickname("민트초코 호빵")
            } else if (color == "navy") {
                setNickname("블루베리 호빵")
            } else if (color == "purple") {
                setNickname("자색고구마 호빵")
            }
        }
    }, [])

  return (
    <div css={theirChatStyles["outerContainer"]}>

        {/* 프로필 사진*/}
        <div>
            <img css={theirChatStyles["profileImg"]} src={red} alt="profileImg" />
        </div>

        <div>
            {/* 닉네임 */}
            <div css={theirChatStyles["nickname"]}>
                {nickname}
            </div>

            {/* 메세지 */}
            <div>
                {messages?.map((item, idx) => (
                    <>
                        <div
                            css={theirChatStyles["chat"]}
                            key={idx}
                        >
                            {item}
                        </div>
                        <br />
                    </>
                ))}
            
            </div>

            {/* 보낸 시간 */}
            <p>{sentAt}</p>
        </div>

    </div>
    
  );
};

export default TheirChat;