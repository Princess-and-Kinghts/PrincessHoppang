// import { css } from "@emotion/react";/
import { useParams } from "react-router-dom";
import { containerStyles, categoryTitleStyles } from "../../styles/PageStyles";

const PostDetail = () => {
  // 현재 URL 정보를 가져옵니다.
  const { id } = useParams();

  return (
    <div css={containerStyles}>
      <div css={categoryTitleStyles}>게시물</div>
      <p>게시물 ID: {id}</p>
    </div>
  );
};

export default PostDetail;
