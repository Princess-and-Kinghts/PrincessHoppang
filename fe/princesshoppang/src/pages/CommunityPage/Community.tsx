import { useState } from "react";
import { css } from "@emotion/react";
import { useNavigate } from "react-router-dom";
import Fonts from "../../styles/Fonts";
import Colors from "../../styles/Colors";
import { containerStyle, categoryTitleStyle } from "../../styles/PageStyles";
import Button from "../../components/Button";
import CategoryType from "./CategoryType";

import WriteIconImg from "../../assets/WriteIcon.png";
import ViewIconImg from "../../assets/ViewIcon.png";
import EmotionIconImg from "../../assets/EmotionIcon.png";
import CommentIconImg from "../../assets/CommentIcon.png";
import pc from "../../assets/pc.png";

const dummyData = [
  {
    id: 1,
    mbtiType: "ENTP",
    mbti: "ENTJ",
    date: "23.08.27",
    title:
      "대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중대충 ENTP에게 시비거는중",
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
  const [category, setCategory] = useState("");

  const toPostDetail = (id: number) => {
    navigate(`/community/post/${id}`);
  };

  const toCreatePost = () => {
    navigate("/community/create");
  };

  return (
    <div css={containerStyle}>
      <div css={categoryTitleStyle}>MBTI톡</div>
      <div>
        <Button shapeType="pill">New</Button>
        <Button shapeType="pill">Hot</Button>
      </div>
      <div css={menuContainerStyle}>
        <div css={mbtiContainerStyle}>
          <CategoryType
            value={category}
            onChange={(selectedCategory) => setCategory(selectedCategory)}
          />
        </div>
        <div css={writeBtnContainerStyle}>
          <button css={writeBtnStyle} onClick={() => toCreatePost()}>
            <img src={WriteIconImg} alt="Write" />
          </button>
        </div>
      </div>
      <div css={postContainerStyle}>
        {dummyData.map((post) => (
          <div
            key={post.id}
            css={postStyle}
            onClick={() => toPostDetail(post.id)}
          >
            <div css={textContainer}>
              <div css={upTitleStyle}>
                <span css={{ fontWeight: "bold" }}>{post.mbtiType} · </span>
                <span css={{ color: Colors.gray }}>익명의 {post.mbti} · </span>
                <span css={{ color: Colors.gray }}>{post.date}</span>
              </div>
              <div css={titleStyle}>{post.title}</div>
              <div css={valueStyle}>
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

const menuContainerStyle = css`
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
`;

const mbtiContainerStyle = css`
  max-width: 700px;
  min-width: 350px;

  @media (max-width: 768px) {
    width: 50%;
  }
`;

const writeBtnContainerStyle = css`
  display: flex;
  align-items: flex-end;
`;

const writeBtnStyle = css`
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

const postContainerStyle = css`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`;

const postStyle = css`
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
  max-width: 400px;
  @media (max-width: 768px) {
    max-width: 200px;
  }
`;

const upTitleStyle = css`
  margin-bottom: 10px;
`;

const titleStyle = css`
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;

const valueStyle = css`
  display: flex;
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
