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
import api from "../../api/axios";

const BoardForm = () => {
  const navi = useNavigate();
  const { boardNo } = useParams();
  const isEdit = boardNo != null;
  // const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [id, setId] = useState("");
  const [status, setStatus] = useState("");
  const [pwd, setPwd] = useState("");
  const [loading, isLoading] = useState(false);

  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState("");

  // 카테고리 목록 불러오기
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await api.get("/boards/category");
        setCategories(res.data.data || res.data);
      } catch (err) {
        console.error("카테고리 불러오기 실패", err);
      }
    };

    fetchCategories();
  }, []);

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
    fd.append("categoryNo", category);
    fd.append("boardTitle", title);
    fd.append("boardContent", content);
    fd.append("userId", id);
    fd.append("userPwd", pwd);

    try {
      if (isEdit) {
        await api.patch(`/boards/${boardNo}`, fd);
        navi(`/boards/${boardNo}`);
      } else {
        await api.post("/boards", fd);
        navi("/boards");
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
    api.get(`/boards/${boardNo}`).then((result) => {
      const data = result.data?.data || result.data;
      if (data) {
        setCategory(String(data.categoryNo || data.category));
        setTitle(data.boardTitle);
        setContent(data.boardContent);
        setId("");
        setPwd("");
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
        {/*        
        <Input
          placeholder="카테고리 번호"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />
         */}
        <select
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          style={{ width: "100%", padding: "8px", borderRadius: "4px" }}
        >
          <option value="" disabled>
            카테고리를 선택하세요
          </option>
          {categories.map((cat) => (
            <option key={cat.categoryNo} value={cat.categoryNo}>
              {cat.categoryName}
            </option>
          ))}
        </select>
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
        <GhostButton onClick={() => navi("/boards")}>취소</GhostButton>
        <Button onClick={onSubmit}>등록</Button>
      </Actions>

      {status && <Message>{status}</Message>}
    </Page>
  );
};
export default BoardForm;
