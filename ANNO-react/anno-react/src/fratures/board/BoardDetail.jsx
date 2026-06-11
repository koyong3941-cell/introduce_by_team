import { useNavigate, useParams } from "react-router-dom";
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
  DetailTitle,
  MetaRow,
  Content,
  FileBox,
  Actions,
  GhostButton,
  DangerButton,
} from "./styles/Board.styles";
import { useState, useEffect } from "react";
import axios from "axios";

const BoardDetail = () => {
  const navi = useNavigate();
  const [board, setBoard] = useState(null);
  const [loading, isLoading] = useState(true);
  const { boardNo } = useParams();

  useEffect(() => {
    isLoading(true);
    axios
      .get(`http://localhost/api/boards/${boardNo}`)
      .then((result) => {
        setBoard(result.data);
      })
      .catch(() => {
        isLoading(false);
      })
      .finally(() => {
        isLoading(false);
      });
  }, [boardNo]);

  if (loading) {
    return (
      <Page>
        <Loading>게시글을 불러오는 중 입니다...</Loading>
      </Page>
    );
  }
  if (!board) {
    return (
      <Page>
        <Empty>존재하지 않는 게시글입니다.</Empty>
      </Page>
    );
  }
  return (
    <Page>
      <DetailTitle>{board.boardTitle}</DetailTitle>
      <MetaRow>
        <span>
          <AnimalName regDate={board.regDate} />
        </span>
        <span>{" • "}</span>
        <span>조회수 : {board.boardCount}</span>
        <span>{" • "}</span>
        <span>{board.regDate}</span>
      </MetaRow>

      <Content>{board.boardContent}</Content>

      <Actions>
        <GhostButton onClick={() => navi("/boards")}>목록</GhostButton>
        <GhostButton onClick={() => navi(`/boards/${boardNo}/edit`)}>
          수정하기
        </GhostButton>
        <DangerButton onClick={onDelete}>삭제</DangerButton>
      </Actions>

      <CommentSection boardNo={boardNo} />
    </Page>
  );
};

export default BoardDetail;
