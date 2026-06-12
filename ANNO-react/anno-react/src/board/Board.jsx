import { useNavigate, useParams } from "react-router-dom";
import {
  Actions,
  Button,
  Field,
  GhostButton,
  Input,
  Label,
  Message,
  Page,
  PageTitle,
  Textarea,
  TopBar,
} from "./styles/Board.styles";
import { useEffect, useState } from "react";

const Board = () => {
  const navi = useNavigate();
  const { boardNo } = useParams();
  const isEdit = boardNo != null;
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [id, setId] = useState("");
  const [status, setStatus] = useState("");
  const [pwd, setPwd] = useState("");
  const [loading, isLoading] = useState(false);

  const onSubmit = async () => {
    if (
      !category.trim() ||
      !title.trim() ||
      !content.trim() ||
      !id.trim() ||
      !pwd.trim()
    ) {
      setStatus("모든 항목을 입력하세요");
      return;
    }
    isLoading(true);
    setStatus("");

    const fd = new FormData();
    fd.append("CategoryNo", category);
    fd.append("boardTitle", title);
    fd.append("boardContent", content);
    fd.append("userId", id);
    fd.append("userPwd", pwd);

    try {
      if (isEdit) {
        await api.patch(`/board/${boardNo}`, fd);
        navi(`/board/${boardNo}`);
      } else {
        await api.post("/board", fd);
        navi("/board");
      }
    } catch (err) {
      setStatus("게시글 작성 실패");
      console.log(err.response);
    } finally {
      isLoading(false);
    }
  };

  useEffect(() => {
    if (!isEdit) return;
    api.get(`/board/${boardNo}`).then((result) => {
      const data = result.data;
      if (data) {
        setCategory(String(data.category));
        setTitle(data.boardTitle);
        setContent(data.boardContent);
        setId(data.userId);
      }
    });
  }, [boardNo, isEdit]);

  return (
    <Page>
      <TopBar>
        <PageTitle>게시글작성</PageTitle>
      </TopBar>
      <Field>
        <Label>카테고리</Label>
        <Input
          placeholder="카테고리 번호"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />
      </Field>

      <Field>
        <Label>제목</Label>
        <Input
          placeholder="제목을 입력하세요"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </Field>

      <Field>
        <Label>내용</Label>
        <Textarea
          placeholder="내용을 입력하세요"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
      </Field>

      <Field>
        <Label>작성자</Label>
        <Input
          placeholder="닉네임"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
      </Field>

      <Field>
        <Label>비밀번호</Label>
        <Input
          placeholder="비밀번호"
          type="password"
          value={pwd}
          onChange={(e) => setPwd(e.target.value)}
        />
      </Field>

      <Actions>
        <GhostButton onClick={() => navi("/board")}>취소</GhostButton>
        <Button onClick={onSubmit}>등록</Button>
      </Actions>

      {status && <Message>{status}</Message>}
    </Page>
  );
};
export default Board;
