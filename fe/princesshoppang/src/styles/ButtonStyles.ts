import { css } from "@emotion/react";
import Fonts from "./Fonts";
import Colors from "./Colors";

export const confirmButtonStyle = css`
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

  // 앱
  @media (max-width: 767px) {
    width: calc(100% - 56px);
    height: 46px;
    margin: 28px 28px;
    border-radius: 15px;
  }
`;

export const pillButtonStyle = css`
  width: 68px;
  height: 29px;
  margin: 15px;
  border: none;
  border-radius: 20px;
  background-color: ${Colors.navy.origin};
  font-size: ${Fonts.fontsize.h4};
  font-weight: bold;
  cursor: pointer;
  text-align: center;
`;
