import { useEffect, useState } from "react";
import RED from "../assets/game/character/RED1.png"
import { theirChatStyles } from "../styles/TheirChatStyles";
import { GameChat } from "../types/ChatTypes";

type TheirChatProps = {
    type: "game" | "chat";
    message?: GameChat;
    // color: "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
    color: "RED" | "ORANGE" | "YELLOW" | "GREEN" | "BLUE" | "NAVY" | "PURPLE";
};

const TheirChat = ({
    type,
    message,
    color,
}: TheirChatProps) => {
    
    const [ nickname, setNickname ] = useState("")
    const [ sentTime, setSentTime ] = useState("");

    useEffect(()=>{
        // 시간 포맷 변경
        if (message?.sentAt !== undefined) {
            const sentTime = new Date(message?.sentAt);
            setSentTime(sentTime.getHours + ":" + sentTime.getMinutes());
        }

        if ( type == "chat") {
            setNickname("진짜 닉네임")
        } else {
            
        }
    }, [])

  return (
    <div css={theirChatStyles["outerContainer"]}>

        {/* 프로필 사진*/}
        <div>
            <img css={theirChatStyles["profileImg"]} src={RED} alt="profileImg" />
        </div>


        <div>
            {/* 닉네임 */}  
                <div css={theirChatStyles["nickname"]}>
                    {message?.nickname}
                </div>
            {/* 메세지 */}
                {
                    message?.messages.map((item, idx) => (
                        <div
                            css={theirChatStyles["chat"]}
                            key={idx}
                        >
                            {item}
                        </div>
                    ))
                }
                <div>{ sentTime }</div>
                    <br />            
        </div>
    </div>
    
  );
};

export default TheirChat;