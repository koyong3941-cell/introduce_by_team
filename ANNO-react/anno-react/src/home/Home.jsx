import {
  Card,
  Content,
  Footer,
  Grid,
  Header,
  Time,
  Writer,
  Title,
} from "./Home.styles";

const Home = () => {
  return (
    <Grid>
      <Card>
        <Header>
          <Writer>😊익명게시판~~</Writer>
          <Time>3분만 맛보세요~</Time>
        </Header>

        <Title>오늘 무슨 댓글을 달까?</Title>

        <Content>
          하늘을 바라보고 공기를 쐬다보니 내가 살아있음을 느꼈다...
          좋다좋다~~히히
        </Content>

        <Footer>
          <span>😊</span>
          <span>💕</span>
          <span>👍</span>
        </Footer>
      </Card>
    </Grid>
  );
};
export default Home;
