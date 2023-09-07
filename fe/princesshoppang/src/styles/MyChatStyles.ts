import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const myChatStyles = {
  outerContainer: css`
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: flex-end;
    padding: 13px 15px;
    
    // 앱
    @media (max-width: 767px) {
      padding: 1px 2px;
      border-radius: 0px;
    }
  `, 

  // 말풍선
  red: css`
    display: inline-block;
    word-wrap: break-word;
    height: auto;
    padding: 10px;
    max-width: 40vw;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.red.origin};
    color: ${Colors.white};
    font-size: ${Fonts.fontsize.h4};
    
    // 앱
    @media (max-width: 767px) {
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};

    }
  `,
  
};

export default myChatStyles;