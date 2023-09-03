import Fonts from "./Fonts";
import Colors from "./Colors";
import { css } from "@emotion/react";

export const containerStyle = css`
  width: 80vw;
  margin: 0 auto;
  padding: 20px;
`;

export const categoryTitleStyle = css`
  font-size: ${Fonts.fontsize.h1};
  font-weight: bold;
  margin-bottom: 40px;
`;

export const divideLineStyle = css`
  width: 80vw;
  height: 1.5px;
  margin: 0 auto;
  background-color: ${Colors.lightgray};

  @media (max-width: 768px) {
    width: 100vw;
  }
`;
