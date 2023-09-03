import { css } from "@emotion/react";
import TitleBackgroundImg from "../../assets/TitleBackgroundImage.png";
import Colors from "../../styles/Colors";
import Fonts from "../../styles/Fonts";
const ProfileSet = () => {
  return (
    <div css={profileContainer}>
      <div css={profileCircleStyle}>
        <img src={TitleBackgroundImg} alt="mbti호빵" />
      </div>
      <div css={profileTextContainer}>
        <div css={profileTitleStyle}>mbti 사냥사냥꾼</div>
        <div css={profileNicknameStyle}>익명의 ESFP 호빵 </div>
      </div>
    </div>
  );
};

export default ProfileSet;

const profileContainer = css`
  display: flex;
  justify-contetn: center;
  align-items: center;
  padding-bottom: 5px;
`;

const profileCircleStyle = css`
  width: 40px;
  height: 40px;
  margin-top: 5px;
  border: 2px solid ${Colors.white};
  border-radius: 50%;
  overflow: hidden;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

const profileTextContainer = css`
  margin-left: 5px;
`;

const profileTitleStyle = css`
  display: inline-block;
  padding: 3px 5px;
  margin-bottom: 2px;
  border: 0.5px solid ${Colors.black};
  border-radius: 15px 15px 15px 0px;
  background-image: url(${TitleBackgroundImg});
  background-position: center center;
  background-size: 100px auto;
  white-space: nowrap;
  font-size: ${Fonts.fontsize.h5};
  font-weight: bold;
`;

const profileNicknameStyle = css`
  font-size: ${Fonts.fontsize.h4};
  font-weight: bold;
`;
