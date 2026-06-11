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
import AnimalName from "../../components/AnimalName";
import { useState, useEffect } from "react";
import axios from "axios";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const navi = useNavigate();
  const [page, setPage] = useState(0);
  const [loading, isLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost/api/boards", { params: { page } })
      .then((result) => {
        // console.log(result);
        setBoards([...result.data]);
      })
      .catch(() => {
        setBoards([]);
      })
      .finally(() => {
        isLoading(false);
      });
  }, [page]);

  return (
    <Page>
      <TopBar>
        <PageTitle>게시판</PageTitle>
        <Button>글쓰기</Button>
      </TopBar>
      {loading ? (
        <Loading>게시글을 불러오는 중입니다...</Loading>
      ) : boards.length === 0 ? (
        <Empty>아직 등록된 게시글이 없습니다.</Empty>
      ) : (
        <List>
          {boards.map((b) => (
            <Item key={b.boardNo} onClick={() => navi(`/boards/${b.boardNo}`)}>
              <ItemTitle>{b.boardTitle}</ItemTitle>
              <ItemMeta>
                <AnimalName regDate={b.regDate} /> • 조회수 : {b.boardCount} •{" "}
                {b.regDate}
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
          disabled={loading || boards.length < 3}
        >
          다음
        </PagerButton>
      </Pager>
    </Page>
  );
};

export default BoardList;
