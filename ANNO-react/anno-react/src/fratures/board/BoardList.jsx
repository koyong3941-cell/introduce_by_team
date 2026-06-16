import { useNavigate } from "react-router-dom";
import {
  Page,
  TopBar,
  PageTitle,
  Button,
  List,
  Item,
  ItemTitle,
  ItemMeta,
  Empty,
  Pager,
  PagerButton,
  Loading,
} from "./styles/Board.styles";

import { useState, useEffect } from "react";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(true);

  const navi = useNavigate();
  const { user } = useAuth();

  // 🔥 ADMIN 판별 (안전하게 기본 false 방지)
  const isAdmin = user?.role === "ROLE_ADMIN";
  console.log(user?.role === "ROLE_ADMIN");

  useEffect(() => {
    const fetchBoards = async () => {
      setLoading(true);

      const url = isAdmin
        ? "http://localhost/api/admins/boards"
        : "http://localhost/api/boards";

      const token = localStorage.getItem("token");

      try {
        const result = await axios.get(url, {
          params: { page },
          headers: token ? { Authorization: `Bearer ${token}` } : {},
        });
        console.log(result);
        setBoards(result.data.data || []);
      } catch (err) {
        console.log(err.response);
        setBoards([]);
      } finally {
        setLoading(false);
      }
    };

    fetchBoards();
  }, [page, isAdmin]); // 🔥 isAdmin 변경 시 재호출

  return (
    <Page>
      <TopBar>
        <PageTitle>게시판</PageTitle>

        {isAdmin && (
          <div style={{ color: "red", fontSize: "12px" }}>
            ADMIN MODE (삭제 포함 조회)
          </div>
        )}

        <Button onClick={() => navi(`/boards/write`)}>글쓰기</Button>
      </TopBar>

      {loading ? (
        <Loading>게시글을 불러오는 중입니다...</Loading>
      ) : boards.length === 0 ? (
        <Empty>아직 등록된 게시글이 없습니다.</Empty>
      ) : (
        <List>
          {boards.map((b) => (
            <Item key={b.boardNo} onClick={() => navi(`/boards/${b.boardNo}`)}>
              <ItemTitle>
                [{b.categoryName}] {b.boardTitle}
              </ItemTitle>

              <ItemMeta>
                {b.userId} • 조회수 : {b.boardCount} • {b.formattedRegDate}
                {isAdmin && (
                  <span style={{ marginLeft: "8px", color: "red" }}>
                    (DEL_YN: {b.delYn ?? "N"})
                  </span>
                )}
              </ItemMeta>
            </Item>
          ))}
        </List>
      )}

      <Pager>
        <PagerButton
          onClick={() => setPage((p) => Math.max(0, p - 1))}
          disabled={page === 0 || loading}
        >
          이전
        </PagerButton>

        <span>{page + 1} 페이지</span>

        <PagerButton
          onClick={() => setPage((p) => p + 1)}
          disabled={loading || boards.length < 10}
        >
          다음
        </PagerButton>
      </Pager>
    </Page>
  );
};

export default BoardList;
