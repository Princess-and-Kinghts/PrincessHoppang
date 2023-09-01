import { css } from "@emotion/react";
import { useNavigate } from "react-router-dom";
import Fonts from "../../styles/Fonts";
import Colors from "../../styles/Colors";
import { containerStyles, categoryTitleStyles } from "../../styles/PageStyles";

import Button from "../../components/Button";
import WriteIconImg from "../../assets/WriteIcon.png";
import ViewIconImg from "../../assets/ViewIcon.png";
import EmotionIconImg from "../../assets/EmotionIcon.png";
import CommentIconImg from "../../assets/CommentIcon.png";
import pc from "../../assets/pc.png";

const mbtiMenuTypes = [
  "ISTJ",
  "ISTP",
  "ISFJ",
  "ISFP",
  "INTJ",
  "INTP",
  "INFJ",
  "INFP",
  "ESTJ",
  "ESTP",
  "ESFJ",
  "ESFP",
  "ENTJ",
  "ENTP",
  "ENFJ",
  "ENFP",
];

const dummyData = [
  {
    id: 1,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 2,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 3,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 4,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 5,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 6,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
  {
    id: 7,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title: "대충 ENTP에게 시비거는중",
    views: 121,
    emotions: 3,
    comments: 4,
    imageUrl: "your-image-url-1.jpg",
  },
];

const Community = () => {
  const navigate = useNavigate();

  const toPostDetail = (id: number) => {
    navigate(`/community/post/${id}`);
  };

  return (
    <div css={containerStyles}>
      <div css={categoryTitleStyles}>MBTI톡</div>
      <div>
        <Button type="pill">New</Button>
        <Button type="pill">Hot</Button>
      </div>
      <div css={menuContainerStyles}>
        <div css={mbtiContainerStyles}>
          <div>
            {mbtiMenuTypes.map((item, idx) => (
              <Button type="pill" key={idx}>
                {item}
              </Button>
            ))}
          </div>
        </div>
        <div css={writeBtnContainerStyles}>
          <button css={writeBtnStyles}>
            <img src={WriteIconImg} alt="Write" />
          </button>
        </div>
      </div>
      <div css={postContainerStyles}>
        {dummyData.map((post) => (
          <div
            key={post.id}
            css={postStyles}
            onClick={() => toPostDetail(post.id)}
          >
            <div css={textContainer}>
              <div css={upTitleStyles}>
                <span css={{ fontWeight: "bold" }}>{post.mbtiType} · </span>
                <span css={{ color: Colors.gray }}>익명의 {post.mbti} · </span>
                <span css={{ color: Colors.gray }}>{post.date}</span>
              </div>
              <div css={titleStyles}>{post.title}</div>
              <div css={valueStyles}>
                <img src={ViewIconImg} alt="view" />
                <span>{post.views}</span>
                <img src={EmotionIconImg} alt="emotion" />
                <span>{post.emotions}</span>
                <img src={CommentIconImg} alt="comment" />
                <span>{post.comments}</span>
              </div>
            </div>
            <div css={postImgContainer}>
              <div>
                <img src={pc} alt="img" />
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Community;

const menuContainerStyles = css`
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
`;

const mbtiContainerStyles = css`
  max-width: 700px;
  min-width: 350px;

  @media (max-width: 768px) {
    width: 50%;
  }
`;

const writeBtnContainerStyles = css`
  display: flex;
  align-items: flex-end;
`;

const writeBtnStyles = css`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
  padding: 10px;
  margin: 10px;
  border: none;
  border-radius: 50px;
  background-color: ${Colors.red.light};
  cursor: pointer;
  transition: background-color 0.2s ease;

  &:hover {
    background-color: ${Colors.red.origin};
  }

  @media (max-width: 768px) {
    position: fixed;
    bottom: 10px;
    right: 10px;
  }

  img {
    width: 30px;
    height: 30px;
  }
`;

const postContainerStyles = css`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`;

const postStyles = css`
  display: flex;
  flex-direction: row;
  width: 100vw;
  padding: 20px;
  margin-bottom: 10px;
  border: 1px solid ${Colors.lightgray};
  border-radius: 15px;
  font-size: ${Fonts.fontsize.h4};
  transition: border 0.5s ease;

  &:hover {
    border: 1px solid ${Colors.gray};
  }
`;

const textContainer = css`
  flex: 4;
`;

const upTitleStyles = css`
  margin-bottom: 10px;
`;

const titleStyles = css`
  margin-bottom: 10px;
`;

const valueStyles = css`
  display: flex;
  justify-contents: center;
  align-items: center;
  color: ${Colors.gray};

  img {
    width: auto;
    height: 12px;
    margin-right: 3px;
  }

  span {
    margin-right: 8px;
  }
`;

const postImgContainer = css`
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;

  div {
    border-radius: 15px;
    // border: 2px solid ${Colors.lightgray};
    overflow: hidden;

    img {
      width: 100%;
      max-width: 70px;
      height: 100%;
      max-height: 70px;
      object-fit: cover;
    }
  }
`;
