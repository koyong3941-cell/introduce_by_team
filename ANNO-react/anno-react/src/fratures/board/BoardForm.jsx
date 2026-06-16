import { useNavigate, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";
import {
  Page,
  TopBar,
  PageTitle,
  Button,
  GhostButton,
  Input,
  Label,
  Field,
  Textarea,
  Actions,
  Message,
} from "./styles/Board.styles";

const BoardForm = () => {
  const navi = useNavigate();
  const { boardNo } = useParams();
  const isEdit = boardNo != null;
  const { user } = useAuth(); // 토큰 정보 사용

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [id, setId] = useState("");
  const [pwd, setPwd] = useState("");
  const [status, setStatus] = useState("");
  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState("");
  const [loading, isLoading] = useState(false);

  // 헤더 설정 함수 (토큰 체크)
  const getHeaders = () => {
    const token = localStorage.getItem("token");
    return token ? { Authorization: `Bearer ${token}` } : {};
  };

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await axios.get("http://localhost/api/boards/category");
        setCategories(res.data.data || res.data);
      } catch (err) {
        console.error("카테고리 불러오기 실패", err);
      }
    };
    fetchCategories();
  }, []);

  useEffect(() => {
    if (!isEdit) return;
    axios.get(`http://localhost/api/boards/${boardNo}`).then((result) => {
      const data = result.data?.data || result.data;
      if (data) {
        setCategory(String(data.categoryNo || data.category));
        setTitle(data.boardTitle);
        setContent(data.boardContent);
      }
    });
  }, [boardNo, isEdit]);

  const onSubmit = async () => {
    if (
      !category ||
      !title.trim() ||
      !content.trim() ||
      !id.trim() ||
      !pwd.trim()
    ) {
      setStatus("모든 항목을 입력하세요");
      return;
    }

    isLoading(true);
    const fd = new FormData();
    fd.append("categoryNo", category);
    fd.append("boardTitle", title);
    fd.append("boardContent", content);
    fd.append("userId", id);
    fd.append("userPwd", pwd);

    try {
      if (isEdit) {
        // 수정 요청
        await axios.patch(`http://localhost/api/boards/${boardNo}`, fd, {
          headers: getHeaders(),
        });
        navi(`/boards/${boardNo}`);
      } else {
        // 등록 요청
        await axios.post("http://localhost/api/boards", fd, {
          headers: getHeaders(),
        });
        navi("/boards");
      }
    } catch (err) {
      setStatus("게시글 처리 실패");
      console.log(err.response);
    } finally {
      isLoading(false);
    }
  };

  return (
    <Page>
      <TopBar>
        <PageTitle>{isEdit ? "게시글 수정" : "게시글 작성"}</PageTitle>
      </TopBar>
      <Field>
        <Label>카테고리</Label>
        <select
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          style={{ width: "100%", padding: "8px" }}
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
        <Input value={title} onChange={(e) => setTitle(e.target.value)} />
      </Field>
      <Field>
        <Label>내용</Label>
        <Textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
      </Field>
      <Field>
        <Label>작성자</Label>
        <Input value={id} onChange={(e) => setId(e.target.value)} />
      </Field>
      <Field>
        <Label>비밀번호</Label>
        <Input
          type="password"
          value={pwd}
          onChange={(e) => setPwd(e.target.value)}
        />
      </Field>
      <Actions>
        <GhostButton onClick={() => navi(-1)}>취소</GhostButton>
        <Button onClick={onSubmit} disabled={loading}>
          {loading ? "처리중..." : "등록"}
        </Button>
      </Actions>
      {status && <Message>{status}</Message>}
    </Page>
  );
};

export default BoardForm;
