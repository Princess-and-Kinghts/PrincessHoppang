import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const inputBoxStyles = {
  outerContainer: css`
    display: flex;
    flex-direction:row;
    justify-content: space-between;
    align-items: center;
    width: 60vw;
    height: 70px;
    border: none;
    border-radius: 15px;
    background-color: ${Colors.white};
    font-size: ${Fonts.fontsize.h3};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    position: absolute;
    bottom: 20px;
    
    // 앱
    @media (max-width: 767px) {
      height: 50px;
      width: 100%;
      border-radius: 0px;
      bottom: 0;
    }
  `, 

  vote: css`
    margin-right: 0px;
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
  `,

  textarea: css`
    margin: 2% 0;
    flex-grow: 1;
    border: none;
    margin-left: 10px;
    margin-right: 5px;

  `,

  // 메세지를 입력하지 않았을 때 버튼
  deactivated: css`
    align-self: center;
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.lightgray};
    color: ${Colors.gray};
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;

    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,

  // 테마별 색깔 버튼
  RED: css`
    
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.red.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  ORANGE: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.orange.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  YELLOW: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.yellow.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  GREEN: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.green.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  BLUE: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.blue.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  NAVY: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-right: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.navy.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  PURPLE: css`
    width: 62px;
    height: 47px;
    margin-right: 10px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 10px;
    background-color: ${Colors.purple.origin};
    color: white;
    font-size: ${Fonts.fontsize.h4};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;
    // 앱
    @media (max-width: 767px) {
      height: 2rem;
      border-radius: 10px;
      font-size: ${Fonts.fontsize.h5};
    }
  `,
  
};

export default inputBoxStyles;