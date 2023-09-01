import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const buttonStyles = {
  confirm: css`
    width: 100%;
    max-width: 334px;
    height: 70px;
    padding: 13px 15px;
    border: none;
    border-radius: 50px;
    background-color: ${Colors.red.light};
    font-size: ${Fonts.fontsize.h3};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;

    &:hover {
      background-color: ${Colors.red.origin};
      color: ${Colors.white};
    }

    // 앱
    @media (max-width: 767px) {
      width: calc(100% - 56px);
      height: 46px;
      margin: 28px 28px;
      border-radius: 15px;
    }
  `,
  pill: css`
    width: 68px;
    height: 29px;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    border: none;
    border-radius: 20px;
    background-color: ${Colors.lightgray};
    font-size: ${Fonts.fontsize.h3};
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition:
      background-color 0.2s ease,
      color 0.1s ease;

    &:hover {
      background-color: ${Colors.navy.origin};
      color: ${Colors.white};
    }
  `,
};

export default buttonStyles;
