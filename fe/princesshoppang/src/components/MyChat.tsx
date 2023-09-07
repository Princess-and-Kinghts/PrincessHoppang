import { useEffect, useState } from "react";
import red from "../assets/game/red.jpg";
import { myChatStyles } from "../styles/MyChatStyles";

type MyChatProps = {
    type: "game" | "chat";
    messages?: string[];
    color: "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
    sentAt?: String;
};

const MyChat = ({
    type,
    messages,
    color,
    sentAt
}: MyChatProps) => {
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
    <div css={myChatStyles["outerContainer"]}>

        
            {/* 메세지 */}
            <div>
                {messages?.map((item, idx) => (
                    <>
                        <div
                            css={myChatStyles["red"]}
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

    
  );
};

export default MyChat;