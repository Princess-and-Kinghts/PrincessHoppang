import { useState, useRef } from "react";
import { css } from "@emotion/react";
import { divideLineStyle } from "../../styles/PageStyles";
import Fonts from "../../styles/Fonts";
import Colors from "../../styles/Colors";
import CategoryType from "./CategoryType";
import FileUpload from "./FileUpload";
import Button from "../../components/Button";
import { useNavigate } from "react-router-dom";

const PostEdit = () => {
  const postId = useRef(0);
  const userId = 23;

  const [selectedFiles, setSelectedFiles] = useState<File[]>([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [category, setCategory] = useState("");

  const navigate = useNavigate();

  const toDetail = () => {
    navigate("/community/post/1");
  };

  const handleFilesSelect = (files: File[]) => {
    setSelectedFiles(files);
  };

  const handleFormSubmit = async () => {
    const formData = new FormData();
    const createdAt = new Date().getTime().toString();
    const newId = (postId.current += 1).toString();

    formData.append("created_at", createdAt);
    formData.append("post_id", newId);
    formData.append("userID", userId.toString());

    formData.append("title", title);
    formData.append("content", content);
    formData.append("category", category);

    if (selectedFiles.length > 0) {
      selectedFiles.forEach((file, idx) => {
        formData.append(`file${idx + 1}`, file);
      });
    }
    console.log(createdAt, newId, title, content, category);
    console.log(formData);
    console.log(formData.get("title"));

    // 추후 fetch
    try {
      await fetch("/api/submit-form", {
        method: "POST",
        body: formData,
      });
      toDetail();
    } catch (e) {
      console.log(e);
    }

    toDetail();
  };

  return (
    <div css={postContainer}>
      <form>
        <div css={postTitleContainer}>
          <input
            css={postTitleStyle}
            type="text"
            value={title}
            placeholder="제목을 입력하세요"
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div css={divideLineStyle} />
        <div css={postContentConTainer}>
          <textarea
            css={postContentStyle}
            value={content}
            placeholder="내용을 입력하세요"
            onChange={(e) => setContent(e.target.value)}
          />
        </div>
        <div css={divideLineStyle} />
        <div css={postCategoryContainer}>
          <div css={postCategoryTextStyle}>
            카테고리를 선택해주세요 (필수 1개)
          </div>
          <div css={postCategoryBtnContainer}>
            <div css={postCategoryBtnSubContainer}>
              <CategoryType
                value={category}
                onChange={(selectedCategory) => setCategory(selectedCategory)}
              />
            </div>
          </div>
        </div>
        <div css={divideLineStyle} />
        <div css={postFileContainer}>
          <div css={postCategoryTextStyle}>사진 등록 (선택 / 최대 5장)</div>
          <FileUpload onFilesSelect={handleFilesSelect} />
        </div>
        <div css={postFileBtnContainer}>
          <Button
            shapeType="confirm"
            onClick={() => handleFormSubmit()}
            type="button"
          >
            글 작성하기
          </Button>
        </div>
      </form>
    </div>
  );
};

export default PostEdit;

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
  border: none;
  padding: 0;
  margin: 0;
  background: none;
  font: inherit;
  color: inherit;
  outline: none;
  width: 100%;
  font-size: ${Fonts.fontsize.h2};
`;

const postContentConTainer = css`
  padding: 20px;
  margin-bottom: 60px;
`;

const postContentStyle = css`
  width: 100%;
  height: 300px;
  border: none;
  padding: 0;
  margin: 0;
  background: none;
  font: inherit;
  color: inherit;
  outline: none;
  white-space: pre-line;
  word-break: break-all;
  resize: none;
`;

const postCategoryContainer = css`
  padding: 20px;
`;
const postCategoryTextStyle = css`
  color: ${Colors.gray};
  font-size: ${Fonts.fontsize.h3};
  margin-bottom: 10px;
`;

const postCategoryBtnContainer = css`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const postCategoryBtnSubContainer = css`
  max-width: 625px;
  min-width: 312px;

  @media (max-width: 768px) {
    width: 50%;
  }
`;

const postFileContainer = css`
  padding: 20px;
`;

const postFileBtnContainer = css`
  display: flex;
  justify-content: center;
  align-item: center;
`;
