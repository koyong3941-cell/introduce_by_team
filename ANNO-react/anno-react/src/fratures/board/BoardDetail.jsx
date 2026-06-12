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
import AnimalName from "../../components/AnimalName";

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
        setBoard(result.data.data);
      })
      .catch(() => {
        alert("상세 조회에 실패했습니다.");
      })
      .finally(() => {
        isLoading(false);
      });
  }, [boardNo]);

  const onDelete = async () => {
    if (!confirm("정말 삭제하시겠어요?")) return;

    try {
      await axios.delete(`http://localhost/api/boards/${boardNo}`);
      navi("/boards");
    } catch {
      alert("삭제에 실패했습니다.");
    }
  };

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
      <DetailTitle>[카테고리] {board.boardTitle}</DetailTitle>
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
    </Page>
  );
};

export default BoardDetail;
