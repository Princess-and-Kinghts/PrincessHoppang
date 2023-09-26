import { useEffect } from "react";

import { css } from "@emotion/react";
import Colors from "../../styles/Colors";

import RED1 from "../../assets/game/character/RED1.png"
import ORANGE1 from "../../assets/game/character/ORANGE1.png"
import YELLOW1 from "../../assets/game/character/YELLOW1.png"
import GREEN1 from "../../assets/game/character/GREEN1.png"
import BLUE1 from "../../assets/game/character/BLUE1.png"
import NAVY1 from "../../assets/game/character/NAVY1.png"
import PURPLE1 from "../../assets/game/character/PURPLE1.png"

import { useAtomValue, useSetAtom } from "jotai";
import { gameChatAtom, introAtom, myColorAtom, myNicknameAtom, titleAtom } from "../../store/GameState";

const Intro = () => {
    const setIntro = useSetAtom(introAtom);
    const setGameChat = useSetAtom(gameChatAtom);

    const title = useAtomValue(titleAtom);
    const myNickname = useAtomValue(myNicknameAtom);
    const myColor = useAtomValue(myColorAtom);
 
    useEffect(() => {
        // 10초후에 화면 전환
        setTimeout(() => {
            setIntro(false);
            setGameChat(true);
        }, 10000);
    },[])

    var character: { [key: string]: JSX.Element }  = {
        RED: <img css={imgSize} src={RED1} alt="character"></img>,
        ORANGE: <img css={imgSize} src={ORANGE1} alt="character"></img>,
        YELLOW: <img css={imgSize} src={YELLOW1} alt="character"></img>,
        GREEN: <img css={imgSize} src={GREEN1} alt="character"></img>,
        BLUE: <img css={imgSize} src={BLUE1} alt="character"></img>,
        NAVY: <img css={imgSize} src={NAVY1} alt="character"></img>,
        PURPLE: <img css={imgSize} src={PURPLE1} alt="character"></img>
    }

    var guide: { [key: string]: JSX.Element } = {
        E: <div></div>,
        I: <div></div>,
        N: <div></div>,
        S: <div></div>,
        T: <div></div>,
        F: <div></div>,
        P: <div></div>,
        J: <div></div>
    }


    return (
        <div css={outerContainer}>
            <br />
            <br />
            <div>{title}!</div>

            {character[myColor]}

            <div>당신은</div>
            <div>{myNickname}</div>
            <br />

            <div>E를 연기해내세요.</div>
            <br />
            <br />
        </div>
    );
};

export default Intro;

const outerContainer = css`
    diplay: flex;
    justify-content: center;
    text-align: center;
    width: 30vw;
    background-color: ${Colors.white};
    border-radius: 20px;
    margin: auto;
    @media (max-width: 767px) {
        width: 70vw;
    }
`

const imgSize = css`
    width: 100%;
`