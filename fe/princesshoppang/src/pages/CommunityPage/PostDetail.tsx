import { css } from "@emotion/react";
import React, { useState } from "react";
import { useParams } from "react-router-dom";

// import { useParams } from "react-router-dom";
import { divideLineStyle } from "../../styles/PageStyles";
import ProfileSet from "./ProfileSet";
import Fonts from "../../styles/Fonts";
import Colors from "../../styles/Colors";
import ViewIconImg from "../../assets/ViewIcon.png";
import EmotionIconImg from "../../assets/EmotionIcon.png";
import CommentIconImg from "../../assets/CommentIcon.png";
import CommentSubmitImg from "../../assets/CommentSubmitImage.png";

import EmotionIconImg1 from "../../assets/emotion/Emotion1.png";
import EmotionIconImg2 from "../../assets/emotion/Emotion2.png";
import EmotionIconImg3 from "../../assets/emotion/Emotion3.png";
import EmotionIconImg4 from "../../assets/emotion/Emotion4.png";
import EmotionIconImg5 from "../../assets/emotion/Emotion5.png";
import EmotionIconImg6 from "../../assets/emotion/Emotion6.png";
import EmotionIconImg7 from "../../assets/emotion/Emotion7.png";
import { useNavigate } from "react-router-dom";

const dummyData = [
  { id: 1, iconImg: EmotionIconImg1, label: "좋아!", count: 0 },
  { id: 2, iconImg: EmotionIconImg2, label: "ㅇㅈ", count: 4 },
  { id: 3, iconImg: EmotionIconImg3, label: "엥?", count: 42 },
  { id: 4, iconImg: EmotionIconImg4, label: "오호라?", count: 4 },
  { id: 5, iconImg: EmotionIconImg5, label: "킹받네;", count: 8 },
  { id: 6, iconImg: EmotionIconImg6, label: "ㅠㅠ", count: 4 },
  { id: 7, iconImg: EmotionIconImg7, label: "아아앗", count: 1 },
];

const btnColors = [
  Colors.red.origin,
  Colors.orange.origin,
  Colors.yellow.origin,
  Colors.green.origin,
  Colors.blue.origin,
  Colors.navy.origin,
  Colors.purple.origin,
];

type EmotionButtonProps = {
  emotionId: number;
  iconImg: string;
  label: string;
  count: number;
  onEmotionClick: (emotionId: number) => void;
};

const EmotionBtn = React.memo(
  ({
    emotionId,
    iconImg,
    label,
    count,
    onEmotionClick,
  }: EmotionButtonProps) => {
    // 글자에 해당 Id의 색깔
    const getBtnColor = () => {
      return {
        color: btnColors[emotionId - 1],
      };
    };

    return (
      <div css={emotionBtnContainer}>
        <img
          css={emotionBtnStyle}
          src={iconImg}
          alt={`Emotion${emotionId}`}
          onClick={() => onEmotionClick(emotionId)}
        />
        <div css={getBtnColor()}>{label}</div>
        <div css={getBtnColor()}>{count}</div>
      </div>
    );
  }
);

const PostDetail = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const postId = parseInt(id || "", 10);
  const [emotionData] = useState(dummyData);

  const [counts, setCounts] = useState([0, 0, 0, 0, 0, 0, 0]);

  const handleEmotionClick = (emotionId: number) => {
    // 해당 감정 버튼의 count를 1 증가시킴
    const newCounts = [...counts];
    newCounts[emotionId - 1] += 1;
    setCounts(newCounts);
  };

  const toCommunity = () => {
    navigate("/community");
  };

  const toEdit = (postId: number) => {
    navigate(`/edit/${postId}`);
  };

  return (
    <div css={postContainer}>
      <div css={postTitleContainer}>
        <div css={postTitleStyle}>글제목</div>
      </div>
      <div css={divideLineStyle} />
      <div css={menuContainer}>
        <div css={menuBtnStyle} onClick={() => toCommunity()}>
          목록
        </div>
        <div css={subMenuContainer}>
          <div css={menuBtnStyle} onClick={() => toEdit(postId)}>
            수정
          </div>
          <div css={menuBtnStyle}>삭제</div>
        </div>
      </div>
      <div css={postContentConTainer}>
        <p css={postContentStyle}>글내용</p>
      </div>
      <div css={emotionContainer}>
        <div css={sqaureContainer}>
          {emotionData.map((item, idx) => (
            <EmotionBtn
              key={item.id}
              emotionId={item.id}
              iconImg={item.iconImg}
              label={item.label}
              count={counts[idx]}
              onEmotionClick={handleEmotionClick}
            />
          ))}
        </div>
      </div>
      <div css={divideLineStyle} />
      <div css={postInfoContainer}>
        <ProfileSet />
        <div css={postRecordContainer}>
          <div css={valueStyle}>
            <img src={ViewIconImg} alt="view" />
            <span>5</span>
            <img src={EmotionIconImg} alt="emotion" />
            <span>978</span>
            <img src={CommentIconImg} alt="comment" />
            <span>354</span>
          </div>
          <div css={postDateStyle}>23.08.27 14:23</div>
        </div>
      </div>
      <div css={boldDivideLineStyle} />

      <div css={commentContainer}>
        <ProfileSet />
        <div css={commentContentContainer}>
          <div css={commentContentStyle}>댓글</div>
          <span css={commentDateStyle}>23.08.27 14:23</span>
          <span css={replyOpenBtnStyle}>대댓글 쓰기</span>
          <div css={replyContainer}>
            <ProfileSet />
            <div css={commentContentContainer}>
              <div css={commentContentStyle}>댓글이다</div>
              <span css={commentDateStyle}>23.08.27 14:23</span>
              <span css={replyOpenBtnStyle}>대댓글 쓰기</span>
            </div>
          </div>
          <div css={replyContainer}>
            <ProfileSet />
            <div css={commentContentContainer}>
              <div css={commentContentStyle}>댓글이다</div>
              <span css={commentDateStyle}>23.08.27 14:23</span>
              <span css={replyOpenBtnStyle}>대댓글 쓰기</span>
            </div>
          </div>
        </div>
      </div>
      <div css={CommentInputContainer}>
        <textarea
          css={commentInputStyle}
          placeholder="댓글을 입력하세요"
        ></textarea>
        <div css={commentSubmitBtn}>
          <img src={CommentSubmitImg} alt="제출" />
        </div>
      </div>
      <div css={emptyStyle}></div>
    </div>
  );
};

export default PostDetail;

const postContainer = css`
  width: 80vw;
  margin: 0 auto;

  @media (max-width: 768px) {
    width: 100vw;
  }
`;

const postTitleContainer = css`
  padding: 20px;
  display: flex;
  align-items: center;
`;

const postTitleStyle = css`
  font-size: ${Fonts.fontsize.h2};
  font-weight: bold;
`;

const menuContainer = css`
  display: flex;
  justify-content: space-between;
  padding: 20px;
  color: ${Colors.gray};
  font-size: ${Fonts.fontsize.h3};
`;

const subMenuContainer = css`
  display: flex;

  div {
    margin-left: 20px;
  }
`;

const menuBtnStyle = css`
  transition: color 0.2s ease;

  &:hover {
    color: ${Colors.navy.origin};
    font-weight: bold;
  }
`;

const postContentConTainer = css`
  padding: 20px;
  margin-bottom: 60px;
`;

const postContentStyle = css`
  line-height: 1.5;
  white-space: pre-line;
  word-break: break-all;
`;

const emotionContainer = css`
  display: flex;
  justify-content: center;
  align-itmes: center;
  margin-bottom: 60px;
`;

const sqaureContainer = css`
  width: 350px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-itmes: center;
`;

const emotionBtnContainer = css`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 10px;
  font-size: ${Fonts.fontsize.h5};
  font-weight: bold;
  // &:hover {
  //   div {
  //     color: black;
  //   }
  // }
`;

const emotionBtnStyle = css`
  width: 48px;
  height: auto;
  padding: 5px;
  cursor: pointer;
`;

const postInfoContainer = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
`;

const postRecordContainer = css`
  font-size: ${Fonts.fontsize.h4};
`;

const valueStyle = css`
  display: flex;
  justify-content: end;
  align-items: center;
  color: ${Colors.gray};
  margin-botom: 2px;

  img {
    width: auto;
    height: 10px;
    margin-right: 3px;
  }

  span {
    margin-right: 8px;
  }
`;

const postDateStyle = css`
  display: flex;
  justify-content: end;
  align-items: center;
  margin-right: 8px;
  color: ${Colors.gray};
`;

const boldDivideLineStyle = css`
  ${divideLineStyle};

  height: 3px;
`;

const commentContainer = css`
  padding: 20px 20px 0px 20px;

  span {
    font-size: ${Fonts.fontsize.h4};
  }
`;

const commentContentContainer = css`
  margin-left: 0.7rem;
`;

const commentContentStyle = css`
  white-space: pre-line;
  word-break: break-all;
  font-size: ${Fonts.fontsize.h4};
`;

const commentDateStyle = css`
  color: ${Colors.gray};
`;

const replyOpenBtnStyle = css`
  color: ${Colors.navy.dark};
  margin-left: 7px;

  &:hover {
    cursor: pointer;
    font-weight: bold;
  }
`;

const replyContainer = css`
  ${commentContainer}

  margin-left:30px;
  padding-right: 0;
`;

const CommentInputContainer = css`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;

  @media (max-width: 767px) {
    width: 90vw;
    position: fixed;
    bottom: 0;
    background-color: ${Colors.white};
  }
`;

const commentInputStyle = css`
  border: none;
  outline: none;
  resize: vertical;
  overflow-y: auto;
  width: calc(100% - 32px);
  height: 42px;
  padding: 10px 10px;
  margin-right: 8px;
  border-radius: 15px;
  font-size: ${Fonts.fontsize.h4};
  background-color: ${Colors.lightgray};
`;

const commentSubmitBtn = css`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 42px;
  height: 42px;
  border: none;
  border-radius: 50%;
  background-color: ${Colors.red.light};
  cursor: pointer;
  transition:
    background-color 0.2s ease,
    color 0.1s ease;

  img {
    width: 25px;
    height: auto;
  }

  &:hover {
    background-color: ${Colors.red.origin};
    color: ${Colors.white};
  }
`;

const emptyStyle = css`
  @media (max-width: 767px) {
    height: 100px;
  }
`;
