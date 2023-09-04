import React, { useState } from "react";
import { css } from "@emotion/react";
import Colors from "../../styles/Colors";
import Fonts from "../../styles/Fonts";

type FileUploadProp = {
  onFilesSelect: (files: File[]) => void;
};

const FileUpload = ({ onFilesSelect }: FileUploadProp) => {
  // 프리뷰 정보 저장
  const [filePreviews, setFilePreviews] = useState<
    { file: File; previewUrl: string }[]
  >([]);

  // 파일 리스트 저장
  const [selectedFiles, setSelectedFiles] = useState<File[]>([]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;

    // 최대 5장까지
    if (files && files.length + selectedFiles.length <= 5) {
      const newFiles = Array.from(files);
      const previews: { file: File; previewUrl: string }[] = [];

      for (let i = 0; i < newFiles.length; i++) {
        const file = newFiles[i];
        const reader = new FileReader();

        reader.onload = (e) => {
          previews.push({ file, previewUrl: e.target?.result as string });
          if (previews.length === newFiles.length) {
            setFilePreviews([...filePreviews, ...previews]);
            setSelectedFiles([...selectedFiles, ...newFiles]);
            // 제출용
            onFilesSelect([...selectedFiles, ...newFiles]);
          }
        };

        reader.readAsDataURL(file);
      }
    }
  };

  // 삭제시 파일과 프리뷰 모두 삭제
  const handleRemoveFile = (idx: number) => {
    const updatedFiles = [...selectedFiles];
    const updatedPreviews = [...filePreviews];

    updatedFiles.splice(idx, 1);
    updatedPreviews.splice(idx, 1);

    setSelectedFiles(updatedFiles);
    setFilePreviews(updatedPreviews);
  };

  return (
    <div>
      <div css={fileContainer}>
        {/* 선택된 파일의 프리뷰 */}
        {filePreviews.map((prev, idx) => (
          <div key={idx} css={filePreviewStyle}>
            <img src={prev.previewUrl} alt={`File ${idx}`} css={fileImgStyle} />
            <button css={removeBtnStyle} onClick={() => handleRemoveFile(idx)}>
              X
            </button>
          </div>
        ))}
        {/* 추가가 가능할 때 추가되는 빈 화면 */}
        {selectedFiles.length < 5 && (
          <label htmlFor="fileInput" css={fileSelectorStyle}>
            +
            <input
              id="fileInput"
              type="file"
              accept="image/*"
              multiple
              css={{ display: "none" }}
              onChange={handleFileChange}
            />
          </label>
        )}
      </div>
    </div>
  );
};

export default FileUpload;

const fileContainer = css`
  display: flex;
  flex-wrap: wrap;
`;

const filePreviewStyle = css`
  width: 100px;
  height: 100px;
  margin: 10px;
  position: relative;
`;

const fileImgStyle = css`
  width: 100%;
  height: 100%;
  object-fit: cover;
`;

const removeBtnStyle = css`
  position: absolute;
  top: 5px;
  right: 5px;
  color: ${Colors.white};
  border: none;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: ${Colors.lightgray};
  cursor: pointer;
`;

const fileSelectorStyle = css`
  width: 100px;
  height: 100px;
  margin: 10px;
  border: 2px solid ${Colors.lightgray};
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: ${Fonts.fontsize.h1};
  color: ${Colors.gray};
`;
