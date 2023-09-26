import { useEffect, useState } from "react";
import red from "../assets/game/red.jpg";
import { myChatStyles } from "../styles/MyChatStyles";
import { GameChat } from "../types/ChatTypes";

type MyChatProps = {
    message?: GameChat;
    color: "RED" | "ORANGE" | "YELLOW" | "GREEN" | "BLUE" | "NAVY" | "PURPLE";
    sentAt?: String;
};

const MyChat = ({
    message,
    color,
    sentAt
}: MyChatProps) => {

  return (
    <div css={myChatStyles["outerContainer"]}>
        {
            message?.messages.map((item, idx) => (
                <div
                    css={myChatStyles[color]}
                    key={idx}
                >
                    {item}
                </div>
            ))
        }
    </div>

    
  );
};

export default MyChat;