import { useEffect, useState } from "react";
import red from "../assets/game/red.jpg";
import { myChatStyles } from "../styles/MyChatStyles";
import { GameChat } from "../types/ChatTypes";

type MyChatProps = {
    messages?: GameChat[];
    color: "red" | "orange" | "yellow" | "green" | "blue" | "navy" | "purple";
    sentAt?: String;
};

const MyChat = ({
    messages,
    color,
    sentAt
}: MyChatProps) => {

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
                            {item.message}
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