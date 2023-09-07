import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const theirChatStyles = {
  outerContainer: css`
    display: flex;
    flex-direction: row;
    padding: 13px 15px;
    
    // 앱
    @media (max-width: 767px) {
      padding: 1px 2px;
      border-radius: 0px;
    }
  `, 
  // 프사
  profileImg: css`
    width: 50px;
    border-radius: 50%;
    @media (max-width: 767px) {
        width: 40px;
    }
  `,
    // 닉네임
    nickname: css`
        font-size: ${Fonts.fontsize.h4};
        font-weight: bold;
        margin-left: 10px;
        margin-top: 20px;
        @media (max-width: 767px) {
            margin-top: 15px;
            font-size: ${Fonts.fontsize.h5};
        }
    `,

  // 말풍선
  chat: css`
    display: inline-block;
    word-wrap: break-word;
    height: auto;
    padding: 10px;
    max-width: 40vw;
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.white};
    color: ${Colors.black};
    font-size: ${Fonts.fontsize.h4};
    
    // 앱
    @media (max-width: 767px) {
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  
};

export default theirChatStyles;