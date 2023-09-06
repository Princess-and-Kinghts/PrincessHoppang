import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const inputBoxStyles = {
  outerContainer: css`
    diplay: flex;
    flex-direction:row;
    justify-content: space-between;
    align-content: center;
    width: 800px;
    height: 70px;
    padding: 13px 15px;
    border: none;
    border-radius: 15px;
    background-color: ${Colors.white};
    font-size: ${Fonts.fontsize.h3};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    // 앱
    @media (max-width: 767px) {
      height: 50px;
      width: 100vw;
      padding: 1px 2px;
      border-radius: 0px;
    }
  `,

  textarea: css`
    flex-grow: 3;
    align-self: center;
    border: none;
  `,

  // 메세지를 입력하지 않았을 때 버튼
  deactivated: css`
    flex-grow: 1;
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
  red: css`
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
  orange: css`
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
  `,
  yellow: css`
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
  `,
  green: css`
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
  `,
  blue: css`
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
  `,
  navy: css`
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
  `,
  purple: css`
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
  `,
  
};

export default inputBoxStyles;